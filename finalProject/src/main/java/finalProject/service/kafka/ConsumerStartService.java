package finalProject.service.kafka;

import finalProject.API.kafka.BroadCastServer;
import finalProject.API.kafka.KafkaConsumerServer;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

@Service
public class ConsumerStartService {
    public void execute(){
        // Kafka Consumer 시작
        InetSocketAddress address = new InetSocketAddress("localhost", 8081);
        BroadCastServer server = new BroadCastServer(address);
        server.start();
        System.out.println("WebSocket server started on port: " + server.getPort());
        // Kafka Consumer 실행
        KafkaConsumerServer.startKafkaConsumer();

    }
}
