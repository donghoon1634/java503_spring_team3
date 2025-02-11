package bitc.fullstack503.java503_team3.controller;

import bitc.fullstack503.java503_team3.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import bitc.fullstack503.java503_team3.service.CommentService;


import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    // 게시물의 댓글 목록을 가져오는 API
    @GetMapping("/board/{boardIdx}")
    public ResponseEntity<List<CommentDTO>> getCommentsByBoardIdx(@PathVariable int boardIdx)throws Exception{
        List<CommentDTO> comments = commentService.getCommentsByBoardIdx(boardIdx);
        return ResponseEntity.ok(comments);
    }

    // 댓글을 추가하는 API
    @PostMapping("/add")
    public ResponseEntity<Void> addComment(@RequestBody CommentDTO commentDTO)throws Exception{
        commentService.addComment(commentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 댓글 추천수를 증가시키는 API
    @PostMapping("/recommend/{commentIdx}")
    public ResponseEntity<Void> recommendComment(@PathVariable int commentIdx)throws Exception{
        commentService.incrementRecommend(commentIdx);
        return ResponseEntity.ok().build();
    }
}

