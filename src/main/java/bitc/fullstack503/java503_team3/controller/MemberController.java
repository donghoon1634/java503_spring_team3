package bitc.fullstack503.java503_team3.controller;
import bitc.fullstack503.java503_team3.dto.LoadAddrDTO;
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
@RequestMapping ("/potato")
public class MemberController
{
  @Autowired
  private MemberService memberService;
  @Autowired
  private LoadAddrService loadAddrService;
  
  @GetMapping ({"/", ""})
  public ModelAndView home () throws Exception
  {
    return new ModelAndView ("main");
  }
  
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
      return "redirect:/potato/member";
    }
    MemberDTO member = new MemberDTO ();
    member.setMemberId (id);
    member.setMemberPw (pw);
    boolean isMember = memberService.signIn (member);
    if (isMember)
    {
      MemberDTO memberInfo = memberService.memberInfo (id);
      LoadAddrDTO loadAddrInfo = loadAddrService.selectLoadAddrIdx (memberInfo.getMemberAddr ());
      String memberProfile = memberService.memberProfileHref (id);
      HttpSession session = request.getSession ();
      session.setAttribute ("memberInfo", memberInfo);
      session.setAttribute ("loadAddrInfo", loadAddrInfo);
      session.setAttribute ("memberProfile", memberProfile);
      session.setMaxInactiveInterval (60 * 30);
      return "redirect:/potato";
    }
    else
    {
      redirectAttributes.addFlashAttribute ("errMsg", "존재하지 않는 회원입니다.");
      return "redirect:/potato/member";
    }
  }
  
  @RequestMapping ("/member/signUp")
  public String signUpProcess (@RequestParam ("memberId") String id, @RequestParam ("memberPw") String pw, @RequestParam ("memberNickname") String nickname, @RequestParam ("memberPhone") String phone, @RequestParam ("memberAddr") String addr, @RequestParam ("memberAddrDetail") String addrDetail, RedirectAttributes redirectAttributes) throws Exception
  {
    if (id == null || id.isEmpty () || pw == null || pw.isEmpty () || nickname == null || nickname.isEmpty () || phone == null || phone.isEmpty () || addr == null || addr.isEmpty () || addrDetail == null || addrDetail.isEmpty ())
    {
      redirectAttributes.addFlashAttribute ("errMsg", "모든 항목을 입력해주세요.");
      return "redirect:/potato/member";
    }
    boolean isId = memberService.isMemberId (id);
    boolean isName = memberService.isMemberNickname (nickname);
    if (isId || isName)
    {
      redirectAttributes.addFlashAttribute ("errMsg", "이미 있는 아이디거나 있는 이름입니다.");
      return "redirect:/potato/member";
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
      memberService.signUpInsert (member);
      return "redirect:/potato/member";
    }
  }
  
  @GetMapping ("/member/signOut")
  public String signOutProcess (HttpServletRequest request) throws Exception
  {
    HttpSession session = request.getSession ();
    session.invalidate ();
    // logout 버튼에 reload 응답을 받으면 페이지 리로드하게 하는 함수 추가
    return "redirect:/potato";
  }
}
