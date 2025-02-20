package bitc.fullstack503.java503_team3.controller;

import bitc.fullstack503.java503_team3.dto.LoadAddrDTO;
import bitc.fullstack503.java503_team3.dto.MemberDTO;
import bitc.fullstack503.java503_team3.dto.UserlifeCommentDTO;
import bitc.fullstack503.java503_team3.dto.UserlifeDTO;
import bitc.fullstack503.java503_team3.mapper.BoardMapper;
import bitc.fullstack503.java503_team3.service.BoardService;
import bitc.fullstack503.java503_team3.service.UlCommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/potato")
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private UlCommentService ulCommentService;
    @Autowired
    private HttpSession session;
    @Autowired
    private BoardMapper boardMapper;

    @RequestMapping({"/", ""})
    public String index() {
        return "index";
    }

    //  게시물 목록
//  기존의 @RequestMapping 사용방법에서 URI 를 입력했던 부분을 value 속성으로 변경
//  해당 URI와 통신하는 방식을 method 속성을 통해서 지정할 수 있음
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public ModelAndView selectBoardList(HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("board/boardList");
        HttpSession session = request.getSession();
        MemberDTO memberInfo = (MemberDTO) session.getAttribute("memberInfo");
        LoadAddrDTO memberAddr = (LoadAddrDTO) session.getAttribute("loadAddrInfo");
        if (memberInfo != null) {
            String memberGu = memberAddr.getLoadAddrGu();
            List<UserlifeDTO> boardList = boardService.selectBoardListByLocation(memberGu);
            mav.addObject("boardList", boardList);
            mav.addObject("memberGu", memberGu);
        }
        else {
            // 게시물 목록 조회
            List<UserlifeDTO> boardList = boardService.selectBoardList();
            mav.addObject("boardList", boardList);
//String memberId = memberInfo.getMemberId();
        }
        return mav;
    }

    // 카테고리별 게시물 목록 페이지로 이동
    @GetMapping("/board/category/{ulCate}")
    public String getBoardByCategory(@PathVariable("ulCate") String ulCate, Model model,HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        MemberDTO memberInfo = (MemberDTO) session.getAttribute("memberInfo");
        LoadAddrDTO memberAddr = (LoadAddrDTO) session.getAttribute("loadAddrInfo");
        if (memberInfo != null) {
            String memberGu = memberAddr.getLoadAddrGu();
            List<UserlifeDTO> boardList = boardService.getBoardByCategoryAndLocation(ulCate, memberGu);
            model.addAttribute("boardList", boardList);
            return "/board/boardList";
        }
        else {
            List<UserlifeDTO> boardList = boardService.getBoardByCategory(ulCate);
            model.addAttribute("boardList", boardList);
            return "/board/boardList";
        }
    }

    // 카테고리별- 인기글 목록 페이지로 이동
    @GetMapping("/board/category/popular")
    public String getBoardByCategoryPopular(Model model,HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        MemberDTO memberInfo = (MemberDTO) session.getAttribute("memberInfo");
        LoadAddrDTO memberAddr = (LoadAddrDTO) session.getAttribute("loadAddrInfo");
        if (memberInfo != null) {
            String memberGu = memberAddr.getLoadAddrGu();
            //인기글을 조회수 순으로 가져옴
            List<UserlifeDTO> boardList = boardService.getBoardByCategoryPopularAndLocation(memberGu);
            model.addAttribute("boardList", boardList);
            return "/board/boardList";
        }
        else {
            //인기글을 조회수 순으로 가져옴
            List<UserlifeDTO> boardList = boardService.getBoardByCategoryPopular();
            model.addAttribute("boardList", boardList);
            return "/board/boardList";
        }

    }


    //    게시글 쓰기
// 작성 화면
    @GetMapping("/board/write")
    public String insertBoard(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        MemberDTO memberInfo = (MemberDTO) session.getAttribute("memberInfo");

        // ✅ 로그인하지 않은 경우 -> 로그인 페이지로 이동
        if (memberInfo == null) {
            return "redirect:/member";
        }
        // ✅ 로그인한 경우 -> 게시글 작성 페이지로 이동
        return "boardWrite";
    }


//        return "board/BoardWrite";


    // 등록처리
    @PostMapping("/board/write")
    public String insertBoard(UserlifeDTO ul, MultipartHttpServletRequest multipart, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        MemberDTO memberInfo = (MemberDTO) session.getAttribute("memberInfo");
        LoadAddrDTO loadAddrInfo = (LoadAddrDTO) session.getAttribute("loadAddrInfo");

    ul.setUlMemberId(memberInfo.getMemberId());
    ul.setUlNickname(memberInfo.getMemberNickname());
    ul.setUlPlace(loadAddrInfo.getLoadAddrGu());

        boardService.insertBoard(ul, multipart);
        return "redirect:/potato/board";
    }


    //  @PathVariable : @RequestParam 과 동일한 역할을 하는 어노테이션, REST 방식 사용 시 URI 에 {} 로 지정해 놓은 리소스 값을 받아오는 어노테이션
//  게시물 상세
    @RequestMapping(value = "/board/{ulIdx}", method = RequestMethod.GET)
    public ModelAndView selectBoardDetail(@PathVariable("ulIdx") int ulIdx, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("board/boardDetail");
        UserlifeDTO ul = boardService.selectBoardDetail(ulIdx);
        HttpSession session = request.getSession();
        MemberDTO memberInfo = (MemberDTO) session.getAttribute("memberInfo");
        LoadAddrDTO memberAddr = (LoadAddrDTO) session.getAttribute("loadAddrInfo");
        if (memberInfo != null) {
            String memberGu = memberAddr.getLoadAddrGu();
            // 게시물 번호에 해당하는 댓글 목록 가져오기
            List<UserlifeCommentDTO> ulcomment = ulCommentService.getCommentsByPage(ulIdx, 0, 5);
            int getUlCommentCount = boardService.getUlCommentCount(ulIdx);
            mav.addObject("ul", ul);
            // ulcomment는 댓글정보
            mav.addObject("ulcomment", ulcomment);
            mav.addObject("getUlCommentCount", getUlCommentCount);
            mav.addObject("memberGu", memberGu);
        }
        else {
            // 게시물 번호에 해당하는 댓글 목록 가져오기
            List<UserlifeCommentDTO> ulcomment = ulCommentService.getCommentsByPage(ulIdx, 0, 5);
            int getUlCommentCount = boardService.getUlCommentCount(ulIdx);
            mav.addObject("ul", ul);
            // ulcomment는 댓글정보
            mav.addObject("ulcomment", ulcomment);
            mav.addObject("getUlCommentCount", getUlCommentCount);
        }
        return mav;



    }


    //    게시물 수정
//@PutMapping :  클라이언트에서 데이터 전송방식을 put 로 설정한 URL만 접속
//  @RequestMapping(method = RequestMethod.PUT 과 동일한 방식
    @PutMapping("/board/{ulIdx}")
    public String updateBoard(@PathVariable("ulIdx") int ulIdx, UserlifeDTO ul, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(); // 현재 사용자의 세션 가져오기
        MemberDTO memberInfo = (MemberDTO) session.getAttribute("memberInfo"); // 로그인한 사용자 정보 가져오기

        // ✅ 로그인 여부 확인
        if (memberInfo == null) {
            return "redirect:/member"; // 로그인하지 않은 경우 로그인 페이지로 이동
        }

        // ✅ 해당 게시글 정보 가져오기
        UserlifeDTO existingBoard = boardService.selectBoardDetail(ulIdx);

        // ✅ 게시글이 존재하지 않는 경우 예외 처리
//        if (existingBoard == null) {
//            return "redirect:/error"; // 오류 페이지로 이동 (필요에 따라 변경 가능)
//        }

        // ✅ 로그인한 사용자가 게시글 작성자인지 확인
        if (!existingBoard.getUlMemberId().equals(memberInfo.getMemberId())) {
            return "redirect/potato/board/" + ulIdx; // 작성자가 아니면 수정 불가, 상세 페이지로 이동
        }

        // ✅ 수정 가능 → 게시글 업데이트 진행
        ul.setUlIdx(ulIdx);
        boardService.updateBoard(ul);

        return "redirect:/potato/board/" + ulIdx; // 수정 완료 후 상세 페이지로 이동
    }



    //    게시물 삭제
//@DeleteMapping: 클라이언트에서 데이터 전송방식을 delete 로 설정한 URL만 접속
//  @RequestMapping(method = RequestMethod.delete 과 동일한 방식
    @DeleteMapping("/board/{ulIdx}")
    public ResponseEntity<String> deleteBoard(@PathVariable("ulIdx") int ulIdx, HttpServletRequest request) {
        // 로그인한 사용자 정보 가져오기
        HttpSession session = request.getSession();
        MemberDTO memberInfo = (MemberDTO) session.getAttribute("memberInfo");
        if (memberInfo == null) {
            return ResponseEntity.status(403).body("로그인이 필요합니다.");
        }
        // 로그인한 사용자의 ID
        String memberId = memberInfo.getMemberId();

        // 게시물 작성자 ID 조회
        String postAuthorId = boardService.getBoardAuthorId(ulIdx);
        if (postAuthorId == null) {
            return ResponseEntity.status(404).body("게시물을 찾을 수 없습니다.");
        }

        // 작성자 ID 비교
        if (!memberId.equals(postAuthorId)) {
            return ResponseEntity.status(403).body("삭제 권한이 없습니다.");
        }

        // 삭제 작업 진행
        boardService.deleteBoard(ulIdx);
        return ResponseEntity.ok("삭제 성공");
    }

//        // 게시물 작성자 ID 가져오기
//        String memberId = memberInfo.getMemberId();
//
//        // 게시물 정보 조회 (여기서는 boardService.getBoard() 메서드를 통해 작성자 ID를 가져오는 예시)
//        String postAuthorId = boardService.getBoardAuthorId(ulIdx); // 해당 게시물의 작성자 ID를 가져오는 메서드
//
//        // 작성자 ID 비교
//        if (!memberId.equals(postAuthorId)) {
//            return ResponseEntity.status(403).body("삭제 권한이 없습니다.");
//        }
//
//        // 삭제 작업 진행
//        boardService.deleteBoard(ulIdx);
//        return ResponseEntity.ok("삭제 성공");
//    }
}
//    @PostMapping("/board/delete")
//    public String deleteBoard ( @RequestParam("ulIdx") int ulIdx){
//        boardService.deleteBoard(ulIdx);
//        return "redirect:/board"; // 삭제 후 목록 페이지로 이동
//    }
//}





