package bitc.fullstack503.java503_team3.controller;

import bitc.fullstack503.java503_team3.dto.LoadAddrDTO;
import bitc.fullstack503.java503_team3.dto.MemberDTO;
import bitc.fullstack503.java503_team3.dto.UserlifeCommentDTO;
import bitc.fullstack503.java503_team3.dto.UserlifeDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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

public class CommentController {

    @Autowired
    private UlCommentService ulCommentService;

    // 댓글 등록순
    @ResponseBody
    @GetMapping("/ulComment/asc/{ulIdx}")
    public List<UserlifeCommentDTO> ulCommentAsc(@PathVariable int ulIdx, @RequestParam("offset") int offset,
                                                 @RequestParam("limit") int limit) {
        return ulCommentService.ulCommentAsc(ulIdx, offset, limit);
    }

    // 댓글 최신순
    @ResponseBody
    @GetMapping("/ulComment/desc/{ulIdx}")
    public List<UserlifeCommentDTO> ulCommentDesc(@PathVariable int ulIdx, @RequestParam("offset") int offset,
                                                  @RequestParam("limit") int limit) {
        return ulCommentService.ulCommentDesc(ulIdx, offset, limit);
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
        HttpSession session = request.getSession(); // 현재 사용자의 세션 가져오기
        MemberDTO memberInfo = (MemberDTO) session.getAttribute("memberInfo"); // 로그인한 사용자 정보 가져오기
        LoadAddrDTO loadAddrInfo = (LoadAddrDTO) session.getAttribute("loadAddrInfo");
        if (memberInfo == null) {
            return "redirect:/member";
        }
        else {
            ulcDTO.setUlCommentUlIdx(ulIdx);
            ulcDTO.setUlComMemberId(memberInfo.getMemberId()); // 댓글 작성자의 아이디
            ulcDTO.setUlCommentNickname(memberInfo.getMemberNickname()); // 댓글 작성자의 닉네임
            ulcDTO.setUlCommentLocation(loadAddrInfo.getLoadAddrGu()); // 댓글 작성자의 위치 (필요에 따라 수정)
            ulCommentService.ulCommentInsert(ulcDTO);
            return "redirect:/board/" + ulIdx;
        }
    }

    // 댓글 삭제
//    @DeleteMapping("/board/comment/{ulIdx}")
//    public ResponseEntity<String> ulCommentDelete(@PathVariable("ulIdx") int ulIdx) throws Exception {
//        try {
//            // 댓글 삭제 서비스 호출
//            ulCommentService.ulCommentdelet(ulIdx);
//            // 성공적으로 삭제되면 200 OK 상태 코드와 함께 성공 메시지 반환
//            return ResponseEntity.ok("댓글 삭제 성공");
//        } catch (Exception e) {
//            // 삭제 실패 시 400 상태 코드와 에러 메시지 반환
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("댓글 삭제에 실패했습니다.");
//        }
//    }
//
    @DeleteMapping("/board/{ulIdx}/{ulCommentIdx}")
    public ResponseEntity<String> ulCommentDelete(
            @PathVariable("ulIdx") int ulIdx,
            @PathVariable("ulCommentIdx") int ulCommentIdx,
            HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        MemberDTO memberInfo = (MemberDTO) session.getAttribute("memberInfo");

        // 로그인 여부 확인
        if (memberInfo == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        String loggedInUserId = memberInfo.getMemberId();  // 로그인한 사용자 ID

        // 댓글 작성자 조회
        String commentAuthorId = ulCommentService.getCommentAuthor(ulCommentIdx);

        // 댓글이 존재하지 않는 경우
        if (commentAuthorId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("댓글을 찾을 수 없습니다.");
        }

        // 현재 로그인한 사용자가 댓글 작성자가 아니면 삭제 불가
        if (!loggedInUserId.equals(commentAuthorId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("본인이 작성한 댓글만 삭제할 수 있습니다.");
        }

        // 댓글 삭제 수행
        boolean isDeleted = ulCommentService.deleteComment(ulCommentIdx);

        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 삭제에 실패했습니다.");
        }

        return ResponseEntity.ok("댓글 삭제 성공");
    }



    // 댓글 5개씩
    @GetMapping("/board/{ulIdx}/moreComments")
    @ResponseBody
    public List<UserlifeCommentDTO> getMoreComments(@PathVariable("ulIdx") int ulIdx, @RequestParam("offset") int offset, @RequestParam("limit") int limit, @RequestParam("descOrAsc") String descOrAsc) {
        return ulCommentService.getCommentsByPage(ulIdx, offset, 5, descOrAsc); // offset을 기준으로 댓글 가져오기
    }

    // 댓글 총 개수 요청 처리
    @GetMapping("/board/{ulIdx}/totalCommentCount")
    @ResponseBody
    public ResponseEntity<Map<String, Integer>> getTotalCommentCount(@PathVariable("ulIdx") Long ulIdx) {
        int totalCount = ulCommentService.getTotalCommentCount(ulIdx);
        Map<String, Integer> response = new HashMap<>();
        response.put("totalCount", totalCount);
        return ResponseEntity.ok(response);
    }

    // 댓글목록다시 들고오기
    @ResponseBody
    @GetMapping("/board/{ulIdx}/loadComments")
    public List<UserlifeCommentDTO> getUlCommentByUlIdx(@PathVariable int ulIdx) {
        return ulCommentService.getUlCommentByUlIdx(ulIdx);
    }

}

