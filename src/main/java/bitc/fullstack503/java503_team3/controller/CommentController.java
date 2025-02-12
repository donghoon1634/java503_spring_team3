package bitc.fullstack503.java503_team3.controller;

import bitc.fullstack503.java503_team3.dto.UserlifeCommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import bitc.fullstack503.java503_team3.service.UlCommentService;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private UlCommentService ulCommentService;

    // 게시물의 댓글 목록을 가져오는 API
//    @GetMapping("/comment/{boardIdx}")
//    public ResponseEntity<List<UserlifeCommentDTO>> getCommentsByBoardIdx(@PathVariable int ulCommentIdx)throws Exception{
//        List<UserlifeCommentDTO> ulCommentDTO = commentService.getCommentsByBoardIdx(ulCommentIdx);
//        return ResponseEntity.ok(ulCommentDTO);
//    }

//    // 등록순 댓글 조회
//    @GetMapping("/board/{boardIdx}/oldest")
//    public List<UserlifeCommentDTO> getCommentsOldestFirst(@PathVariable int ulCommentUlIdx) {
//        return ulCommentService.getCommentsOldestFirst(ulCommentUlIdx);
//    }
//
//    // 최신순 댓글 조회
//    @GetMapping("/board/{boardIdx}/newest")
//    public List<UserlifeCommentDTO> getCommentsNewestFirst(@PathVariable int ulCommentUlIdx) {
//        return ulCommentService.getCommentsNewestFirst(ulCommentUlIdx);
//    }
//
//
//
//    // 댓글을 추가하는 API
//    @PostMapping("/add")
//    public ResponseEntity<Void> addComment(@RequestBody UserlifeCommentDTO ulCommentDTO)throws Exception{
//        ulCommentService.addComment(ulCommentDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    // 댓글 추천수를 증가시키는 API
//    @PostMapping("/recommend/{commentIdx}")
//    public ResponseEntity<Void> recommendComment(@PathVariable int ulCommentIdx)throws Exception{
//        ulCommentService.incrementRecommend(ulCommentIdx);
//        return ResponseEntity.ok().build();
//    }

}

