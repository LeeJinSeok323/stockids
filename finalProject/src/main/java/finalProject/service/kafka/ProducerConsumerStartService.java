package finalProject.service.kafka;


import finalProject.API.kafka.KafkaWebSocketServer;
import finalProject.API.kafka.MultiThreadedUDPServer;
import org.springframework.stereotype.Service;

import java.net.InetSocketAddress;

@Service
public class ProducerConsumerStartService {
    //@PostConstruct
    public void init() {
        // Kafka producer 시작
        MultiThreadedUDPServer.getInstance().startServer();

        // Kafka Consumer 시작
        InetSocketAddress address = new InetSocketAddress("localhost", 8080);
        KafkaWebSocketServer server = new KafkaWebSocketServer(address);
        server.start();
        System.out.println("WebSocket server started on port: " + server.getPort());
        // Kafka Consumer 실행
        server.startKafkaConsumer();
    }
}
