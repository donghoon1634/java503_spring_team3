package bitc.fullstack503.java503_team3.controller;

import bitc.fullstack503.java503_team3.dto.UserlifeCommentDTO;
import jakarta.servlet.http.HttpServletRequest;
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
    
    // 댓글 등록순
    @ResponseBody
    @GetMapping("/ulComment/asc/{ulIdx}")
    public List<UserlifeCommentDTO> ulCommentAsc(@PathVariable int ulIdx) {
        return ulCommentService.ulCommentAsc(ulIdx);
    }
    // 댓글 최신순
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
    public String ulCommentInsert(@PathVariable("ulIdx") int ulIdx, UserlifeCommentDTO ulcDTO, HttpServletRequest request) throws Exception {
        ulcDTO.setUlCommentUlIdx(ulIdx);
        ulCommentService.ulCommentInsert(ulcDTO);
        return "redirect:/board/" + ulIdx;
    }
    // 댓글 삭제
    @DeleteMapping("/board/comment/{ulIdx}")
    public ResponseEntity<String> ulCommentDelete(@PathVariable("ulIdx") int ulIdx) throws Exception {
        try {
            // 댓글 삭제 서비스 호출
            ulCommentService.ulCommentdelet(ulIdx);
            // 성공적으로 삭제되면 200 OK 상태 코드와 함께 성공 메시지 반환
            return ResponseEntity.ok("댓글 삭제 성공");
        } catch (Exception e) {
            // 삭제 실패 시 400 상태 코드와 에러 메시지 반환
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("댓글 삭제에 실패했습니다.");
        }
    }
    // 댓글 5개씩
    @GetMapping("/board/{ulIdx}/moreComments")
    @ResponseBody
    public List<UserlifeCommentDTO> getMoreComments(@PathVariable("ulIdx") int ulIdx, @RequestParam("offset") int offset) {
        return ulCommentService.getCommentsByPage(ulIdx, offset, 5); // offset을 기준으로 댓글 가져오기
    }

}

