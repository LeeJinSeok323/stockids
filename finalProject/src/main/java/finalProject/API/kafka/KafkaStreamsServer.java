package finalProject.API.kafka;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;

import java.util.Properties;

public class KafkaStreamsServer {
    public static final String INPUT_TOPIC = "stock-input";
    public static final String OUTPUT_TOPIC = "stock-output";
    public void StartStreams(){
        // Kafka Streams의 속성을 설정하는 객체
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "stock-kafka-streams");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // 문자열 데이터를 직렬화하고 역직렬화 | 파티션이 하나이기 때문에 Key 값 설정 불필요.
        // props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(
                StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG,
                LogAndContinueExceptionHandler.class
        );

        // Kafka Streams의 토폴로지를 정의하는 객체
        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> input = builder.stream(INPUT_TOPIC);

        // 데이터 중 시간값을 추출하여 그룹화
        KStream<String, String> groupedStream = input.map((key, value) -> {
            // 데이터를 파싱
            String[] parts = value.split(":");
            int term = 100;
            int startTime = Integer.parseInt(parts[0].substring(10).trim());
            for (String part : parts) {
                String[] keyValue = part.split("=");
                if (keyValue.length == 2 && keyValue[0].trim().equals("timestamp")) {
                    if(Integer.parseInt(keyValue[1].trim()) > startTime + term)
                        break;
                }
            }
            return new KeyValue<>(String.valueOf(startTime), value);
        });

        // 그룹화된 데이터로 통계 생성
        groupedStream.groupByKey() // Key(시간 그룹) 기준으로 그룹화
                .reduce(
                        (oldValue, newValue) -> StockStatistics.get(oldValue, newValue), // 최신 값 유지
                        Materialized.with(Serdes.String(), Serdes.String()) // Key와 Value의 Serde 설정
                )
                .toStream() // KTable -> KStream 변환
                .to(OUTPUT_TOPIC);



        // 토폴로지를 빌드하여 Kafka Streams 객체 생성
        KafkaStreams streams = new KafkaStreams(builder.build(), props);

        // Kafka Streams를 실행
        streams.start();

        // 애플리케이션 종료 시 Kafka Streams를 정지
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}
