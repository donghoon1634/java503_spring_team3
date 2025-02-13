package bitc.fullstack503.java503_team3.controller;

import bitc.fullstack503.java503_team3.dto.userMyPageDTO;
import bitc.fullstack503.java503_team3.dto.userTradeCommentDTO;
import bitc.fullstack503.java503_team3.service.MyPageService;
import bitc.fullstack503.java503_team3.service.UserTradeCommentService;
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
        private UserTradeCommentService userTradeCommentService;

        @PostMapping( "/tradeChat/{boardIdx}")
        public String tradeChat(@PathVariable("boardIdx") int boardIdx, @ModelAttribute userTradeCommentDTO utc) throws Exception{
        ModelAndView mav = new ModelAndView("usedTrade/tradeChat");
        userTradeCommentService.qnaComment(utc);
        mav.addObject("utc",utc);
        mav.addObject("boardIdx",boardIdx);

            List<userTradeCommentDTO> tradeCommentList = userTradeCommentService.getComment(boardIdx);
            mav.addObject("tradeCommentList",tradeCommentList);
        return "/usedTrade/tradeChat";
        }

    @RequestMapping(value = "/tradeChat/{boardIdx}", method = RequestMethod.GET)
    public String getTradeChat(@PathVariable("boardIdx") int boardIdx, Model model) throws Exception {
            model.addAttribute("boardIdx",boardIdx);
        return "/usedTrade/tradeChat";
    }

        @RequestMapping("/edit")

        public String tradeEdit(){
            return "/usedTrade/sellerProductEdit";
        }

        @RequestMapping("/detail")

        public String tradeDetail(){
            return "/usedTrade/sellerProductDetail";
        }

    @RequestMapping(value = "/myPageEdit", method = RequestMethod.PUT)
    public String updateMyPage(userMyPageDTO myPage) throws Exception{
        myPageService.updateMyPage(myPage);
        return "/myPage/myPage";
    }
    @RequestMapping(value = "/myPage", method = RequestMethod.GET)
    public String getMyPage() {
        return "/myPage/myPage";
    }

}
