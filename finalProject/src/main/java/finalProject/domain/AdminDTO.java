package finalProject.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("adminDTO")
public class AdminDTO {
    String adminNum;
    String adminId;
    String adminPw;
    String adminName;
    String gender;
    Date birth;
    String position;

}
