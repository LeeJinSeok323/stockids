package finalProject.domain;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@Alias("alertListDTO")
public class AlertListDTO {
String alertNum;
String alertContents;
String memberNum;
Date alertDate;
String alertOk;
}
