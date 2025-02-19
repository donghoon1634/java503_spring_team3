package bitc.fullstack503.java503_team3.controller;
import bitc.fullstack503.java503_team3.dto.MemberDTO;
import bitc.fullstack503.java503_team3.service.MemberService;
import bitc.fullstack503.java503_team3.util.MemberProfileUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class Test
{
  @Autowired
  private MemberProfileUtils memberProfileUtils;
  @Autowired
  private MemberService memberService;
  
  @RequestMapping ("/profile")
  public ModelAndView profile (HttpServletRequest request) throws Exception
  {
    ModelAndView mv = new ModelAndView ("/profile");
    HttpSession session = request.getSession ();
    MemberDTO member = (MemberDTO) session.getAttribute ("memberInfo");
    String memberId = member.getMemberId ();
    String memberProfileHref = memberService.memberProfileHref (memberId);
    mv.addObject ("memberProfileHref", memberProfileHref);
    return mv;
  }
  
  @RequestMapping ("/profile/insert")
  public String profileInsert (HttpServletRequest request, MultipartHttpServletRequest multipart) throws Exception
  {
    HttpSession session = request.getSession ();
    MemberDTO member = (MemberDTO) session.getAttribute ("memberInfo");
    String memberId = member.getMemberId ();
    memberService.memberProfile (memberProfileUtils.memberProfileHref (memberId, multipart));
    return "redirect:/profile";
  }
}
