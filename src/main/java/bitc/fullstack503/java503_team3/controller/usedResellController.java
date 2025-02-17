package bitc.fullstack503.java503_team3.controller;

import bitc.fullstack503.java503_team3.dto.userMyPageDTO;
import bitc.fullstack503.java503_team3.dto.userMyPageProductEditDTO;
import bitc.fullstack503.java503_team3.dto.userTradeCommentDTO;
import bitc.fullstack503.java503_team3.service.MyPageService;
import bitc.fullstack503.java503_team3.service.tradeUserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/potato")
public class usedResellController {

    @Autowired
    private MyPageService myPageService;

    @Autowired
    private tradeUserCommentService tradeUserCommentService;

    // 거래문의 게시판 댓글 등록
    @PostMapping("/tradeChat/{tradeBoardIdx}")
    public String tradeChat(@PathVariable("tradeBoardIdx") int tradeBoardIdx, @ModelAttribute userTradeCommentDTO utc) throws Exception {

        if (utc.getTradeUserComment() == null || utc.getTradeUserComment()
                .trim().isEmpty()) {
            utc.setTradeUserComment("");
        }
        tradeUserCommentService.qnaComment(utc);

        List<userTradeCommentDTO> tradeCommentList = tradeUserCommentService.getComment(tradeBoardIdx);

        ModelAndView mav = new ModelAndView("usedTrade/tradeChat");

        mav.addObject("tradeBoardIdx", tradeBoardIdx);
        mav.addObject("tradeCommentList", tradeCommentList);

        return "redirect:/potato/tradeChat/" + tradeBoardIdx;
    }

    // 거래문의 게시판 댓글 보기?
    @RequestMapping(value = "/tradeChat/{tradeBoardIdx}", method = RequestMethod.GET)
    public String getTradeChat(@PathVariable("tradeBoardIdx") int tradeBoardIdx, Model model) throws Exception {

        List<userTradeCommentDTO> tradeCommentList = tradeUserCommentService.getComment(tradeBoardIdx);

        model.addAttribute("tradeBoardIdx", tradeBoardIdx);
        model.addAttribute("tradeCommentList", tradeCommentList);

        return "/usedTrade/tradeChat";
    }


    @RequestMapping("/myPage/edit")

    public String tradeEdit() {

        return "/usedTrade/sellerProductEdit";
    }

    // 안씀 일단은
    @RequestMapping("/detail")

    public String tradeDetail() {
        return "/usedTrade/sellerProductDetail";
    }

//    마이 페이지 자기소개 등록
    @RequestMapping(value = "/myPage/{myPageUser}", method = RequestMethod.PUT)
    public String updateMyPage(userMyPageDTO myPage) throws Exception {
        myPageService.updateMyPage(myPage);
        return "redirect:/potato/myPage/{myPageUser}";
    }

    // 내가 보는 내 정보 마이페이지
    @RequestMapping(value = "/myPage/{myPageUser}", method = RequestMethod.GET)
    public ModelAndView getMyPage(@PathVariable("myPageUser") String myPageUser) throws Exception {
        System.out.println("Received myPageUser: " + myPageUser);
        ModelAndView mav = new ModelAndView("/myPage/myPage");

        List<userMyPageDTO> myPageList = myPageService.selectMyPage(myPageUser);

        // 만약 여러 결과가 있을 경우 첫 번째 결과를 가져올 수도 있음
        if (!myPageList.isEmpty()) {
            mav.addObject("myPage", myPageList.get(0));
        } else {
            mav.addObject("myPage", new userMyPageDTO());
        }

        List<userMyPageProductEditDTO> umpe = myPageService.selectMyPageProduct(myPageUser);
            if(umpe.isEmpty()) {
                System.out.println("상품 없습니다.");
            }
        mav.addObject("myPageUser", myPageUser);
        mav.addObject("umpe", umpe);
        return mav;
    }

    // 남이 보는 내 정보 페이지
    @RequestMapping(value = "/myPage/view/{myPageUser}", method = RequestMethod.GET)
    public ModelAndView getMyPage1(@PathVariable("myPageUser") String myPageUser) throws Exception {
        System.out.println("Received myPageUser: " + myPageUser);
        ModelAndView mav = new ModelAndView("/myPage/myPage2");

        List<userMyPageDTO> myPageList = myPageService.selectMyPage1(myPageUser);

        // 만약 여러 결과가 있을 경우 첫 번째 결과를 가져올 수도 있음
        if (!myPageList.isEmpty()) {
            mav.addObject("myPage", myPageList.get(0));
        } else {
            mav.addObject("myPage", new userMyPageDTO());
        }

        List<userMyPageProductEditDTO> umpe = myPageService.selectMyPageProduct1(myPageUser);
        if(umpe.isEmpty()) {
            System.out.println("상품 없습니다.");
        }
        mav.addObject("myPageUser", myPageUser);
        mav.addObject("umpe", umpe);
        return mav;
    }
}
