package finalProject.API.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class RawStockConsumer {
    public static void startKafkaConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");  // Kafka 서버 주소
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "raw-stock-consumer-server");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("stock-input"));  // 읽고자 하는 Kafka 토픽명
        // Kafka 메시지 수신 및 WebSocket 전송 쓰레드 실행
        new Thread(() -> {
            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                    for (ConsumerRecord<String, String> record : records) {
                        String[] pairs = record.value().split(":");
                        StringBuilder sb = new StringBuilder();
                        sb.append("{");
                        for (String pair : pairs) {
                            String[] keyValue = pair.split("=");
                            String str = "\"" + keyValue[0] + "\":\"" + keyValue[1] + "\",";
                            sb.append(str);
                        }
                        sb.deleteCharAt(sb.length() - 1);
                        sb.append("}");
                        System.out.println("Raw Stock Info = " + sb.toString());

                        BroadCastServer.broadcastMessage(sb.toString());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                consumer.close();
            }
        }).start();
    }
}
