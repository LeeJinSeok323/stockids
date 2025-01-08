package finalProject.service.kafka;


import finalProject.API.kafka.MultiThreadedUDPServer;
import org.springframework.stereotype.Service;

@Service
public class ProducerStartService {
    //@PostConstruct
    public void execute() {
        // Kafka producer 시작
        MultiThreadedUDPServer.getInstance().startServer();

    }
}
