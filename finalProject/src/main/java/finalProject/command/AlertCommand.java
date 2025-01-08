package finalProject.command;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AlertCommand {
    String alertNum;
    String alertContents;
    @DateTimeFormat(pattern="yyy-MM-dd")
    Date alertDate;
}
