package bitc.fullstack503.java503_team3.controller;
import bitc.fullstack503.java503_team3.dto.MemberDTO;
import bitc.fullstack503.java503_team3.service.LoadAddrService;
import bitc.fullstack503.java503_team3.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
public class MemberController
{
  @Autowired
  private MemberService memberService;
  @Autowired
  private LoadAddrService loadAddrService;
  
  @RequestMapping ("/member")
  public ModelAndView member () throws Exception
  {
    ModelAndView mv = new ModelAndView ("/login/member");
    List<String> guList = loadAddrService.selectLoadAddrGu ();
    mv.addObject ("guList", guList);
    return mv;
  }
  
  @RequestMapping ("/member/signIn")
  public String signInProcess (@RequestParam ("memberId") String id, @RequestParam ("memberPw") String pw, RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception
  {
    if (id == null || id.isEmpty () || pw == null || pw.isEmpty ())
    {
      redirectAttributes.addFlashAttribute ("errMsg", "모든 항목을 입력해주세요.");
      return "redirect:/member";
    }
    MemberDTO member = new MemberDTO ();
    member.setMemberId (id);
    member.setMemberPw (pw);
    boolean isMember = memberService.signIn (member);
    if (isMember)
    {
      MemberDTO memberInfo = memberService.memberInfo (id);
      HttpSession session = request.getSession ();
      session.setAttribute ("memberInfo", memberInfo);
      return "redirect:/";
    }
    else
    {
      redirectAttributes.addFlashAttribute ("errMsg", "존재하지 않는 회원입니다.");
      return "redirect:/member";
    }
  }
  
  @RequestMapping ("/member/signUp")
  public String signUpProcess (@RequestParam ("memberId") String id, @RequestParam ("memberPw") String pw, @RequestParam ("memberNickname") String nickname, @RequestParam ("memberPhone") String phone, @RequestParam ("memberAddr") String addr, @RequestParam ("memberAddrDetail") String addrDetail, RedirectAttributes redirectAttributes) throws Exception
  {
    if (id == null || id.isEmpty () || pw == null || pw.isEmpty () || nickname == null || nickname.isEmpty () || phone == null || phone.isEmpty () || addr == null || addr.isEmpty () || addrDetail == null || addrDetail.isEmpty ())
    {
      redirectAttributes.addFlashAttribute ("errMsg", "모든 항목을 입력해주세요.");
      return "redirect:/member";
    }
    boolean isId = memberService.isMemberId (id);
    boolean isName = memberService.isMemberNickname (nickname);
    if (isId || isName)
    {
      redirectAttributes.addFlashAttribute ("errMsg", "이미 있는 아이디거나 있는 이름입니다.");
      return "redirect:/member";
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
  
  @GetMapping ("/member/signOut")
  public ResponseEntity<String> signOutProcess (HttpServletRequest request) throws Exception
  {
    HttpSession session = request.getSession ();
    session.invalidate ();
    // logout 버튼에 reload 응답을 받으면 페이지 리로드하게 하는 함수 추가
    return ResponseEntity.ok ("reload");
  }
}
