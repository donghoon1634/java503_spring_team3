package bitc.fullstack503.java503_team3.controller;
import bitc.fullstack503.java503_team3.dto.*;
import bitc.fullstack503.java503_team3.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
  @Autowired
  private ProductService productService;
  @Autowired
  private ProductService2 productService2;
  @Autowired
  private ProductCommentService productCommentService;
  @Autowired
  private BoardService2 boardService2;
  
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
  
  @RequestMapping ("/myPage/{memberId}")
  public ModelAndView myPage (@PathVariable ("memberId") String memberId, HttpServletRequest request) throws Exception
  {
    HttpSession session = request.getSession ();
    MemberDTO memberInfo = (MemberDTO) session.getAttribute ("memberInfo");
    if (memberInfo == null)
    {
      ModelAndView mv = new ModelAndView ();
      mv.setViewName ("redirect:/potato/member");
      return mv;
    }
    if (!memberInfo.getMemberId ().equals (memberId))
    {
      ModelAndView mv = new ModelAndView ();
      mv.setViewName ("redirect:/potato/myPage/" + memberInfo.getMemberId ());
      return mv;
    }
    ModelAndView mv = new ModelAndView ("/myPage/myPage");
    MemberDTO member = memberService.memberInfo (memberId);
    String memberProfile = memberService.memberProfileHref (memberId);
    String memberContent = memberService.getMemberContent (memberId);
    LoadAddrDTO loadAddrDTO = loadAddrService.selectLoadAddrIdx (member.getMemberAddr ());
    String si = loadAddrDTO.getLoadAddrSido ();
    String gu = loadAddrDTO.getLoadAddrGu ();
    String ro = loadAddrDTO.getLoadAddrRo ();
    String mainNum = loadAddrDTO.getLoadAddrMainNum ();
    String subNum = loadAddrDTO.getLoadAddrSubNum ();
    String addrDetail = member.getMemberAddrDetail ();
    if (subNum == null || subNum.isEmpty ())
    {
      String addr = si + " " + gu + " " + ro + " " + mainNum;
      mv.addObject ("addr", addr);
    }
    else
    {
      String addr = si + " " + gu + " " + ro + " " + mainNum + "-" + subNum;
      mv.addObject ("addr", addr);
    }
    List<ProductDTO> productList = productService2.getMyProductList (memberId);
    List<UserlifeDTO> boardList = boardService2.selectMyBoardList (memberId);
    mv.addObject ("boardList", boardList);
    mv.addObject ("productList", productList);
    mv.addObject ("addrDetail", addrDetail);
    mv.addObject ("memberContent", memberContent);
    mv.addObject ("memberProfile", memberProfile);
    mv.addObject ("member", member);
    return mv;
  }
  
  @RequestMapping ("/myPage/edit/{memberId}")
  public String myPageEdit (@PathVariable ("memberId") String memberId, @RequestParam ("userMyPageContents") String userMyPageContents, @RequestParam ("memberNickname") String memberNickname, @RequestParam ("memberPhone") String memberPhone, HttpServletRequest request) throws Exception
  {
    HttpSession session = request.getSession ();
    MemberDTO memberInfo = (MemberDTO) session.getAttribute ("memberInfo");
    if (memberInfo == null)
    {
      return "redirect:/potato/member";
    }
    if (!memberInfo.getMemberId ().equals (memberId))
    {
      return "redirect:/potato/myPage/" + memberInfo.getMemberId ();
    }
    MemberContentDTO memberContent = new MemberContentDTO ();
    memberContent.setMemberContentId (memberId);
    memberContent.setMemberContent (userMyPageContents);
    memberService.setMemberContent (memberContent);
    MemberDTO member = new MemberDTO ();
    member.setMemberId (memberId);
    member.setMemberNickname (memberNickname);
    member.setMemberPhone (memberPhone);
    memberService.memberUpdate (member);
    return "redirect:/potato/myPage/" + memberId;
  }
  
  @RequestMapping ("/myPage/delete/{memberId}")
  public String myPageDelete (@PathVariable ("memberId") String memberId, HttpServletRequest request) throws Exception
  {
    HttpSession session = request.getSession ();
    MemberDTO memberInfo = (MemberDTO) session.getAttribute ("memberInfo");
    if (memberInfo == null)
    {
      return "redirect:/potato/member";
    }
    if (!memberInfo.getMemberId ().equals (memberId))
    {
      return "redirect:/potato/myPage/" + memberInfo.getMemberId ();
    }
    // memberService.memberDelete (memberId);
    return "redirect:/potato";
  }
  
  @GetMapping ("/yourPage/{memberId}")
  public ModelAndView yourPage (@PathVariable ("memberId") String memberId, HttpServletRequest request) throws Exception
  {
    HttpSession session = request.getSession ();
    MemberDTO memberInfo = (MemberDTO) session.getAttribute ("memberInfo");
    if (memberInfo != null)
    {
      if (memberInfo.getMemberId ().equals (memberId))
      {
        ModelAndView mv = new ModelAndView ();
        mv.setViewName ("redirect:/potato/myPage/" + memberInfo.getMemberId ());
        return mv;
      }
    }
    ModelAndView mv = new ModelAndView ("/myPage/yourPage");
    MemberDTO member = memberService.memberInfo (memberId);
    String memberProfile = memberService.memberProfileHref (memberId);
    String memberContent = memberService.getMemberContent (memberId);
    List<ProductDTO> productList = productService2.getMyProductList (memberId);
    List<UserlifeDTO> boardList = boardService2.selectMyBoardList (memberId);
    mv.addObject ("boardList", boardList);
    mv.addObject ("productList", productList);
    mv.addObject ("memberContent", memberContent);
    mv.addObject ("memberProfile", memberProfile);
    mv.addObject ("member", member);
    return mv;
  }
  
  @GetMapping ("/trade/productDetailQ")
  public ModelAndView productDetailQ (@RequestParam ("productNum") String productNum) throws Exception
  {
    ModelAndView mv = new ModelAndView ("/myPage/tradeChat");
    List<ProductCommentDTO> productCommentList = productCommentService.getProductComment (Integer.parseInt (productNum));
    ProductDTO product = productService.getProductDetail (Integer.parseInt (productNum));
    mv.addObject ("product", product);
    mv.addObject ("productCommentList", productCommentList);
    return mv;
  }
  
  @RequestMapping ("/trade/productDetailQ/edit")
  public String productDetailQEdit (@RequestParam ("productNum") String productNum, @RequestParam ("tradeUserComment") String tradeUserComment, HttpServletRequest request) throws Exception
  {
    HttpSession session = request.getSession ();
    MemberDTO memberInfo = (MemberDTO) session.getAttribute ("memberInfo");
    if (memberInfo == null)
    {
      return "redirect:/potato/trade/productDetailQ?productNum=" + productNum;
    }
    String memberId = memberInfo.getMemberId ();
    String memberNickname = memberInfo.getMemberNickname ();
    ProductCommentDTO productCommentDTO = new ProductCommentDTO ();
    productCommentDTO.setProductCommentMemberId (memberId);
    productCommentDTO.setProductCommentProductIdx (Integer.parseInt (productNum));
    productCommentDTO.setProductCommentContent (tradeUserComment);
    productCommentDTO.setProductCommentMemberNickname (memberNickname);
    productCommentService.insertProductComment (productCommentDTO);
    return "redirect:/potato/trade/productDetailQ?productNum=" + productNum;
  }
  
  @GetMapping ("/trade/productDelete")
  public String productDelete (@RequestParam ("productNum") String productNum, HttpServletRequest request) throws Exception
  {
    HttpSession session = request.getSession ();
    MemberDTO memberInfo = (MemberDTO) session.getAttribute ("memberInfo");
    if (memberInfo == null)
    {
      return "redirect:/potato/trade/productDetail?productNum=" + productNum;
    }
    String memberId = memberInfo.getMemberId ();
    ProductDTO product = productService.getProductDetail (Integer.parseInt (productNum));
    String productMemberId = product.getMemberIdx ();
    if (!memberId.equals (productMemberId))
    {
      return "redirect:/potato/trade/productDetail?productNum=" + productNum;
    }
    else
    {
      productCommentService.deleteProductComment (Integer.parseInt (productNum));
      productService2.deleteProduct (Integer.parseInt (productNum));
      return "redirect:/potato/trade";
    }
  }
}
