package bitc.fullstack503.java503_team3.controller;


import bitc.fullstack503.java503_team3.dto.UserlifeDTO;
import bitc.fullstack503.java503_team3.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board/api")
public class BoardApiController {

    @Autowired
    private BoardService boardService;




    //    추천 수 증가
    @PostMapping("/blog/{ulIdx}")
    public Object plusLike(@PathVariable("ulIdx") int ulIdx) throws Exception {
        return boardService.plusLike(ulIdx);
    }

    //    인기글 정렬
    // 인기글 조회 (조회수 많은 순)
    @GetMapping("/popular")
    public List<UserlifeDTO> getPopularPosts(@RequestParam(defaultValue = "5", value = "limit") int limit) {
        return boardService.getPopularPosts(limit);
    }

}
