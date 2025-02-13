package bitc.fullstack503.java503_team3.controller;

import bitc.fullstack503.java503_team3.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board/api")
public class BoardApiController {
    @Autowired
    private BoardService boardService;




    //    추천 수 증가
    @ResponseBody
    @PostMapping("/blog/{ulIdx}")
    public Object plusLike(@PathVariable("ulIdx") int ulIdx) throws Exception {
        return boardService.plusLike(ulIdx);
    }
}
