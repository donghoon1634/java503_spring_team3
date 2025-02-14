package bitc.fullstack503.java503_team3.controller;
import bitc.fullstack503.java503_team3.dto.MemberDTO;
import bitc.fullstack503.java503_team3.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
@Controller
public class MemberController
{
  @Autowired
  private MemberService memberService;
  
  @RequestMapping ("/member")
  public ModelAndView member () throws Exception
  {
    return new ModelAndView ("login/member");
  }
  
  @RequestMapping ("/member/signUp")
  public String signupProcess (@RequestParam ("memberId") String id, @RequestParam ("memberPw") String pw, @RequestParam ("memberNickname") String nickname, @RequestParam ("memberPhone") String phone, @RequestParam ("memberAddr") String addr, @RequestParam ("memberAddrDetail") String addrDetail) throws Exception
  {
    boolean isId = memberService.isMemberId (id);
    boolean isName = memberService.isMemberNickname (nickname);
    if (isId)
    {
      return "redirect:/signup?errMsg=" + URLEncoder.encode ("이미 있는 아이디거나 있는 이름 입니다.", StandardCharsets.UTF_8);
    }
    else if (isName)
    {
      return "redirect:/signup?errMsg=" + URLEncoder.encode ("이미 있는 아이디거나 있는 이름 입니다.", StandardCharsets.UTF_8);
    }
    else
    {
      MemberDTO member = new MemberDTO ();
      member.setMemberId (id);
      member.setMemberPw (pw);
      member.setMemberNickname (nickname);
      member.setMemberPhone (phone);
      member.setMemberAddr (addr);
      member.setMemberAddrDetail (addrDetail);
      memberService.signUp (member);
      return "redirect:/member";
    }
  }
}
