package bitc.fullstack503.java503_team3.controller;

import bitc.fullstack503.java503_team3.dto.UserlifeCommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import bitc.fullstack503.java503_team3.service.UlCommentService;

import java.util.List;

@Controller

public class CommentController {

    @Autowired
    private UlCommentService ulCommentService;

    @ResponseBody
    @GetMapping("/ulComment/asc/{ulIdx}")
    public List<UserlifeCommentDTO> ulCommentAsc(@PathVariable int ulIdx) {
        return ulCommentService.ulCommentAsc(ulIdx);
    }

    @ResponseBody
    @GetMapping("/ulComment/desc/{ulIdx}")
    public List<UserlifeCommentDTO> ulCommentDesc(@PathVariable int ulIdx) {
        return ulCommentService.ulCommentDesc(ulIdx);
    }
    // 댓글 추천수를 증가시키는 API
    @ResponseBody
    @PostMapping("/ulComment/like/{ulCommentIdx}")
    public Object ulCommentLikeUpDate(@PathVariable("ulCommentIdx") int ulCommentIdx) throws Exception {
        return ulCommentService.ulCommentLikeUpDate(ulCommentIdx);  // ulCommentIdx를 서비스로 전달
    }
    // 댓글 등록 처리

    @PostMapping("/board/{ulIdx}/add")
//    public String addComment(@PathVariable("ulIdx") int ulIdx, @ModelAttribute UserlifeCommentDTO ulCommentDTO) throws Exception {
//        // 댓글 내용을 서비스에 전달하여 추가
//        ulCommentService.addComment(ulCommentDTO);
//        return "redirect:/board/" + ulIdx;  // 게시물 상세 페이지로 리다이렉트

        public ResponseEntity<Void> addComment(@RequestBody UserlifeCommentDTO ulCommentDTO) throws Exception {
            ulCommentService.addComment(ulCommentDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}

