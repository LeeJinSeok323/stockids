package finalProject.API.kafka;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.processor.api.ContextualProcessor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.Stores;

import java.util.Properties;

public class KafkaStreamsServer {
    public static final String INPUT_TOPIC = "stock-input";
    public static final String OUTPUT_TOPIC = "stock-output";
    public static final String STATE_STORE_NAME = "processed-keys";

    public static void StartStreams() {
        // Kafka Streams의 속성을 설정하는 객체
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "stock-kafka-streams");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG,
                LogAndContinueExceptionHandler.class);
        props.put(StreamsConfig.STATE_DIR_CONFIG, "C:\\kafka-streams\\state-store");

        // Kafka Streams의 토폴로지를 정의하는 객체
        StreamsBuilder builder = new StreamsBuilder();

        // 상태 저장소 추가
        builder.addStateStore(
                Stores.keyValueStoreBuilder(
                        Stores.inMemoryKeyValueStore(STATE_STORE_NAME),
                        Serdes.String(),
                        Serdes.String()
                )
        );

        KStream<String, String> input = builder.stream(INPUT_TOPIC);

        // 데이터 중 시간값을 추출하여 그룹화
        KStream<String, String> groupedStream = input.map((key, value) -> {
            String[] parts = value.split(":");
            int timestamp = 0;

            StringBuilder sb = new StringBuilder();
            for (String part : parts) {
                String[] keyValue = part.split("=");
                sb.append(keyValue[0]);
                if (keyValue.length == 2 && keyValue[0].trim().equals("timestamp")) {
                    timestamp = Integer.parseInt(keyValue[1].trim());
                    timestamp -= timestamp % 100;
                    keyValue[1] = String.valueOf(timestamp);
                }
                sb.append("=" + keyValue[1] + ":");
            }
            value = sb.toString().substring(0, sb.toString().length() - 1);
            return new KeyValue<>(String.valueOf(timestamp), value);
        });

        // 그룹화된 데이터로 통계 생성 후 통계
        groupedStream.groupByKey()
                .reduce(
                        (oldValue, newValue) -> StockStatistics.get(oldValue, newValue), // 최신 값 유지
                        Materialized.with(Serdes.String(), Serdes.String())
                )
                .toStream();


        KStream<String, String> processedStream = groupedStream.process(() -> new ContextualProcessor<String, String, String, String>() {
            private KeyValueStore<String, String> stateStore;
            private ProcessorContext<String, String> context;
            @Override
            public void init(ProcessorContext<String, String> context) {
                this.context = context;
                // 상태 저장소 초기화
                this.stateStore = context.getStateStore(STATE_STORE_NAME);
            }

            @Override
            public void process(Record<String, String> record) {
                String key = record.key();
                String value = record.value();

                // 상태 저장소에서 이전 값을 가져옴
                String previousValue = stateStore.get(key);

                // 상태 저장소 업데이트
                stateStore.put(key, value);

                // 키 값이 변경되었을 때만 전송
                if (previousValue == null || !previousValue.equals(value)) {
                    context.forward(record); // 변경된 값만 전송
                }
            }
        }, STATE_STORE_NAME);

        // 최종적으로 데이터를 OUTPUT_TOPIC으로 전송
        processedStream.to(OUTPUT_TOPIC);




        // 토폴로지를 빌드하여 Kafka Streams 객체 생성
        KafkaStreams streams = new KafkaStreams(builder.build(), props);

        // Kafka Streams를 실행
        streams.start();

        // 애플리케이션 종료 시 Kafka Streams를 정지
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}
