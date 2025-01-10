package finalProject.command;

import lombok.Data;

import java.util.Date;

@Data
public class InquireCommand {
    String inquireNum;
    String memberNum;
    String memberName;
    String inquireSubject;
    String inquireContents;
    Date inquireTime;
}
