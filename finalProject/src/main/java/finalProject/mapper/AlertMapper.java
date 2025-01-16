package finalProject.mapper;

import finalProject.domain.AlertDTO;
import finalProject.domain.AlertListDTO;
import finalProject.domain.MemberDTO;
import finalProject.domain.StartEndPageDTO;
import finalProject.domain.alert.AlertMemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface AlertMapper {
    String alertAutoNum(@Param("tableName") String tableName,
                        @Param("columnName") String columnName,
                        @Param("sep") String sep,
                        @Param("padding") int padding);
    int alertInsert(AlertDTO dto);
    public List<AlertDTO> allSelect(StartEndPageDTO sepDTO);
    public int alertCount(String searchWord);
    public List<AlertDTO> getAllAlerts();
    public int alertDelete(@Param("alert") String alert[]);
    void insertAlertToAlertList(@Param("alertNum") String alertNum,
                                @Param("memberNum") String memberNum,
                                @Param("alertDate") Date alertDate);
    List<AlertMemberDTO> getAllMembers();

    List<AlertListDTO> getAlertList(String memberNum);
    List<AlertListDTO> getAllAlertList(String alertNum);
    String getAlertContent(String alertNum);

    void alertSend(AlertListDTO alert);
}

