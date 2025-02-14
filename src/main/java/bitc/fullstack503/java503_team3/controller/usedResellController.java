package bitc.fullstack503.java503_team3.controller;

import bitc.fullstack503.java503_team3.dto.userMyPageDTO;
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

    @RequestMapping(value = "/tradeChat/{tradeBoardIdx}", method = RequestMethod.GET)
    public String getTradeChat(@PathVariable("tradeBoardIdx") int tradeBoardIdx, Model model) throws Exception {

        List<userTradeCommentDTO> tradeCommentList = tradeUserCommentService.getComment(tradeBoardIdx);

        model.addAttribute("tradeBoardIdx", tradeBoardIdx);
        model.addAttribute("tradeCommentList", tradeCommentList);

        return "/usedTrade/tradeChat";
    }

    @RequestMapping("/edit")

    public String tradeEdit() {
        return "/usedTrade/sellerProductEdit";
    }

    @RequestMapping("/detail")

    public String tradeDetail() {
        return "/usedTrade/sellerProductDetail";
    }

    @RequestMapping(value = "/myPage", method = RequestMethod.PUT)
    public String updateMyPage(userMyPageDTO myPage) throws Exception {
        myPageService.updateMyPage(myPage);
        return "redirect:/potato/myPage";
    }

    @RequestMapping(value = "/myPage", method = RequestMethod.GET)
    public String getMyPage() {

        return "/myPage/myPage";
    }


}
