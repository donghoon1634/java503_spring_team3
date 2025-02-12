package bitc.fullstack503.java503_team3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

    @Controller
    @RequestMapping("/potato")
public class usedResellController {

        @RequestMapping("/tradeChat")
        public String tradeChat(){


            return "/tradeChat";
        }

        @RequestMapping("/edit")

        public String tradeEdit(){
            return "sellerProductEdit";
        }

        @RequestMapping("/detail")

        public String tradeDetail(){
            return "sellerProductDetail";
        }

        @RequestMapping("/myPage")

        public String myPage(){
            return "myPage";
        }
    }
