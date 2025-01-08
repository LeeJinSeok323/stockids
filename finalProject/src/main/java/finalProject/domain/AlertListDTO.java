package finalProject.domain;

import lombok.Data;

import java.util.Date;

@Data
public class AlertListDTO {
String alertNum;
String alertContents;
String memberName;
Date alertDate;
}
