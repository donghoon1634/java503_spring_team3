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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.Iterator;
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
    boolean hasFile = false;
    for (Iterator<String> it = multipart.getFileNames (); it.hasNext (); )
    {
      String fileName = it.next ();
      MultipartFile file = multipart.getFile (fileName);
      if (file != null && !file.isEmpty ())
      {
        hasFile = true;
        break;
      }
    }
    if (!hasFile)
    {
      return "redirect:/potato/myPage/" + memberId;
    }
    memberService.memberProfile (memberProfileUtils.memberProfileHref (memberId, multipart));
    return "redirect:/potato/myPage/" + memberId;
  }
}
