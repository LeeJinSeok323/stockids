package finalProject.controller;

import finalProject.command.AlertCommand;
import finalProject.domain.AlertDTO;
import finalProject.domain.AlertListDTO;
import finalProject.domain.MemberDTO;
import finalProject.domain.alert.AlertMemberDTO;
import finalProject.service.admin.*;
import finalProject.service.alert.AlertDataService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("user1")
public class AlertController {

    private final AlertAutoNumService alertAutoNumService;

    public AlertController(AlertAutoNumService alertAutoNumService) {
        this.alertAutoNumService = alertAutoNumService;
    }

    @Autowired
    AlertListService alertListService;
    @RequestMapping(value="alertList",method=RequestMethod.GET)
    public String alertList(
            @RequestParam(value="searchWord",required=false)String searchWord,
            @RequestParam(value="page",required=false,defaultValue="1") int page,
            Model model){
        alertListService.execute(searchWord,model,page);
        return "thymeleaf/admin/alertList";
    }

    @GetMapping("alertWrite")
    public String alertWrite(Model model){
        alertAutoNumService.execute(model);
        return "thymeleaf/admin/alertWrite";
    }
    @Autowired
    AlertWriteService alertWriteService;
    @RequestMapping(value="alertWrite", method= RequestMethod.POST)
    public String saveAlert(AlertCommand alertCommand, BindingResult result, HttpSession session){

        alertWriteService.execute(alertCommand,session);
        return "redirect:/user1/alertList";
    }

    @Autowired
    AlertDeleteService alertDeleteService;
    @PostMapping("alertDelete")
    public String alertDelete(
            @RequestParam(value="alertDels") String alertDels[]){
        alertDeleteService.execute(alertDels);
        return "redirect:/user1/alertList";
    }

    @Autowired
    AlertDataService alertDataService;

    @GetMapping("alertData")
    @ResponseBody
    public ResponseEntity<List<AlertListDTO>> alertData(HttpSession session){
        List<AlertListDTO> dto = alertDataService.execute(session);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("alert/send")
    public String sendAlert(@RequestParam("alertNum") String alertNum, Model model) {
        String alertContent = alertListService.getAlertContent(alertNum);
        List<AlertMemberDTO> memberDTOS = alertListService.getAllMembers(alertNum);
        model.addAttribute("alertContent", alertContent);
        model.addAttribute("memberDTOS", memberDTOS);
        model.addAttribute("alertNum", alertNum);
        return "thymeleaf/admin/alertSend";
    }

    @Autowired
    AlertSendService alertSendService;
    @PostMapping("/send-alert")
    @ResponseBody
    public ResponseEntity<String> sendAlert(
            @RequestParam("alertNum") String alertNum,
            @RequestParam("selectedMembers") List<String> selectedMembers) {
        alertSendService.execute(alertNum, selectedMembers);
        return ResponseEntity.ok("알림이 성공적으로 전송되었습니다.");
    }

}
