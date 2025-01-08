package finalProject.service.kafka;

import finalProject.API.kafka.KafkaStreamsServer;
import org.springframework.stereotype.Service;

@Service
public class StreamsStartService {
    public void execute(){
        KafkaStreamsServer.StartStreams();
    }
}
