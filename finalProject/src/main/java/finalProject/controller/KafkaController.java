package finalProject.controller;

import finalProject.domain.AuthInfoDTO;
import finalProject.service.kafka.ConsumerStartService;
import finalProject.service.kafka.ProducerStartService;
import finalProject.service.kafka.StreamsStartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class KafkaController {
    @Autowired
    ProducerStartService producerStartService;
    @Autowired
    StreamsStartService streamsStartService;
    @Autowired
    ConsumerStartService consumerStartService;

    @RequestMapping("server/start")
    public void start(HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
        if(!auth.isAdmin()){
            return;
        }
        producerStartService.execute();
        streamsStartService.execute();
        consumerStartService.execute();
    }

}
