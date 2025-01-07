package finalProject.controller;

import finalProject.service.kafka.ProducerConsumerStartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class KafkaController {
    @Autowired
    ProducerConsumerStartService producerConsumerStartService;
    @RequestMapping("test")
    public void test() {

    }
}
