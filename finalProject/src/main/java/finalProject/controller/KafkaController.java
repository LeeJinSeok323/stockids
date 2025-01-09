package finalProject.controller;

import finalProject.domain.AuthInfoDTO;
import finalProject.service.kafka.ConsumerStartService;
import finalProject.service.kafka.InsertDealService;
import finalProject.service.kafka.ProducerStartService;
import finalProject.service.kafka.StreamsStartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
    @Autowired
    private InsertDealService insertDealService;

    @RequestMapping("server1")
    public void start(HttpSession session) {
        AuthInfoDTO auth = (AuthInfoDTO) session.getAttribute("auth");
//        if(!auth.isAdmin()){
//            return;
//        }
        producerStartService.execute();
        streamsStartService.execute();
        consumerStartService.execute();
    }


    @ResponseBody
    @RequestMapping("test")
    public void test() {
        insertDealService.execute("{\"timestamp\":\"154200\",\"stockCode\":\"KR7005930003\",\"dealPrice\":\"00000056100\",\"dealVolume\":\"0000000010\",\"totalDealVolume\":\"000000007724\",\"max\":\"56100\",\"min\":\"56100\",\"start\":\"56100\"}");
    }
}
