package finalProject.service.member;

import finalProject.command.MemberCommand;
import finalProject.domain.MemberDTO;
import finalProject.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberInsertService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MemberMapper memberMapper;
    public void execute(MemberCommand memberCommand){
     MemberDTO dto = new MemberDTO();
        MemberDTO memberDTO = new MemberDTO();
        // memberCommand 값을 memberDTO로 변환

        // 자동으로 memberNum을 생성
        String memberNum = memberMapper.memberAutoNum();

        // MemberDTO로 변환하여 회원 정보 설정
        memberDTO.setMemberNum(memberNum);  // 자동 생성된 memberNum 설정
        memberDTO.setMemberId(memberCommand.getMemberId());
        memberDTO.setMemberPw(passwordEncoder.encode(memberCommand.getMemberPw()));
        memberDTO.setMemberName(memberCommand.getMemberName());
        memberDTO.setGender(memberCommand.getGender());
        memberDTO.setMemberBirth(memberCommand.getMemberBirth());
        memberDTO.setNickName(memberCommand.getNickName());

        // DB에 회원 등록
        memberMapper.memberInsert(memberDTO);




    }
}
