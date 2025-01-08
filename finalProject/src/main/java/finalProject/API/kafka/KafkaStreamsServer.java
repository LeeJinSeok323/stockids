package finalProject.API.kafka;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

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

        // Member 역직렬화 설정
        JsonDeserializer<MemberDTO.Create> memberDeserializer = new JsonDeserializer<>(MemberDTO.Create.class);
        memberDeserializer.addTrustedPackages("com.example.*");

        // "member" 토픽에서 문자열 데이터를 읽어서 KTable로 변환
        KTable<String, MemberDTO.Create> table = builder.table(
                TABLE_TOPIC,
                Consumed.with(
                        Serdes.String(),
                        Serdes.serdeFrom(new JsonSerializer<>(), memberDeserializer)
                )
        );

        // Board 역직렬화 설정
        JsonDeserializer<BoardDTO.Create> boardDeserializer = new JsonDeserializer<>(BoardDTO.Create.class);
        boardDeserializer.addTrustedPackages("com.example.*");

        // 스트림 토픽에서 문자열 데이터를 읽어서 KStream으로 변환
        KStream<String, BoardDTO.Create> stream = builder.stream(
                STREAM_TOPIC,
                Consumed.with(
                        Serdes.String(),
                        Serdes.serdeFrom(new JsonSerializer<>(), boardDeserializer)
                )
        );

        // KTable과 KStream을 inner join
        KStream<String, BoardDetailDTO.Create> joined = stream
                // board stream의 키를 memberId로 변경
                .selectKey((key, value) -> value.getMemberId().toString())
                // board stream과 member table을 member_id 키 값으로 조인
                .join(table, (streamValue, tableValue) -> {
                    if (streamValue.getMemberId().equals(tableValue.getMemberId())) {
                        return BoardDetailDTO.Create.builder()
                                .boardId(streamValue.getId())
                                .memberId(streamValue.getMemberId())
                                .nickname(tableValue.getNickName())
                                .city(tableValue.getCity())
                                .phone(tableValue.getPhone())
                                .title(streamValue.getTitle())
                                .body(streamValue.getBody())
                                .modifiedAt(streamValue.getModifiedAt())
                                .createdAt(streamValue.getCreatedAt())
                                .build();
                    } else {
                        return null;
                    }
                });

        // 조인된 스트림을 BoardDetailDTO.Create로 직렬화해서 joined 토픽으로 전송
        joined.to(
                JOINED_TOPIC,
                Produced.with(
                        Serdes.String(),
                        new JsonSerde<>(BoardDetailDTO.Create.class)
                )
        );

        // 토폴로지를 빌드하여 Kafka Streams 객체 생성
        KafkaStreams streams = new KafkaStreams(builder.build(), props);

        // Kafka Streams를 실행
        streams.start();

        // 애플리케이션 종료 시 Kafka Streams를 정지
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}
