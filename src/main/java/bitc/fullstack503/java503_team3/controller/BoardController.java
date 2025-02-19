package bitc.fullstack503.java503_team3.controller;

import bitc.fullstack503.java503_team3.dto.UserlifeCommentDTO;
import bitc.fullstack503.java503_team3.dto.UserlifeDTO;
import bitc.fullstack503.java503_team3.service.BoardService;
import bitc.fullstack503.java503_team3.service.UlCommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/potato")
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private UlCommentService ulCommentService;

    //  게시물 목록
//  기존의 @RequestMapping 사용방법에서 URI 를 입력했던 부분을 value 속성으로 변경
//  해당 URI와 통신하는 방식을 method 속성을 통해서 지정할 수 있음
    @RequestMapping(value = "/userlife", method = RequestMethod.GET)
    public ModelAndView selectBoardList() throws Exception {
        ModelAndView mav = new ModelAndView("board/boardList");
        // 게시물 목록 조회
        List<UserlifeDTO> boardList = boardService.selectBoardList();
        mav.addObject("boardList", boardList);

//        int ulCommentCount = ulCommentService.ulCommentCount(board.getUlIdx());
//        mav.addObject("ulCommentCount", ulCommentCount);

        return mav;
    }

    // 카테고리별 게시물 목록 페이지로 이동
    @GetMapping("/userlife/category/{ulCate}")
    public String getBoardByCategory(@PathVariable("ulCate") String ulCate, Model model) throws Exception {
        List<UserlifeDTO> boardList = boardService.getBoardByCategory(ulCate);
        model.addAttribute("boardList", boardList);
        return "/board/boardList";
    }


    //    게시글 쓰기
// 작성 화면
    @GetMapping("/userlife/write")
    public String insertBoard(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
//        if (session.getAttribute("id") == null) {
//            return "redirect:/login"; // 로그인하지 않았다면 로그인 페이지로 리다이렉트
//        }
//        else {
//            return "board/BoardWrite";
//        }
        return "board/BoardWrite";

    }

    // 등록처리
    @PostMapping("/userlife/write")
    public String insertBoard(UserlifeDTO ul, MultipartHttpServletRequest multipart, HttpServletRequest request) throws Exception {

//        HttpSession session = request.getSession();
//        if (session.getAttribute("id") == null) {
//            return "redirect:/login"; // 로그인하지 않은 겨우
//        }
//        else {
//            boardService.insertBoard(ul);
//            return "redirect:/board";
//        }
        boardService.insertBoard(ul, multipart);
        return "redirect:/potato/userlife";
    }


    //  @PathVariable : @RequestParam 과 동일한 역할을 하는 어노테이션, REST 방식 사용 시 URI 에 {} 로 지정해 놓은 리소스 값을 받아오는 어노테이션
//  게시물 상세
    @RequestMapping(value = "/userlife/{ulIdx}", method = RequestMethod.GET)
    public ModelAndView selectBoardDetail(@PathVariable("ulIdx") int ulIdx) throws Exception {
        ModelAndView mav = new ModelAndView("board/boardDetail");
        UserlifeDTO ul = boardService.selectBoardDetail(ulIdx);

        // 게시물 번호에 해당하는 댓글 목록 가져오기
        List<UserlifeCommentDTO> ulcomment = ulCommentService.getCommentsByPage(ulIdx, 0, 5);

        int getUlCommentCount = boardService.getUlCommentCount(ulIdx);

        mav.addObject("ul", ul);
        // ulcomment는 댓글정보
        mav.addObject("ulcomment", ulcomment);
        mav.addObject("getUlCommentCount", getUlCommentCount);



        return mav;
    }



//    게시물 수정
//@PutMapping :  클라이언트에서 데이터 전송방식을 put 로 설정한 URL만 접속
//  @RequestMapping(method = RequestMethod.PUT 과 동일한 방식
@PutMapping("/userlife/{ulIdx}")
public String updateBoard(@PathVariable("ulIdx") int ulIdx, UserlifeDTO ul) throws Exception {
    ul.setUlIdx(ulIdx);
    System.out.println("수정 요청 제목: " + ul.getUlTitle());
//    ul.setUlUpdateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    boardService.updateBoard(ul);
    return "redirect:/potato/userlife/"+ulIdx;
}


//    게시물 삭제
//@DeleteMapping: 클라이언트에서 데이터 전송방식을 delete 로 설정한 URL만 접속
//  @RequestMapping(method = RequestMethod.delete 과 동일한 방식
@DeleteMapping("/userlife/{ulIdx}")
public ResponseEntity<String> deleteBoard(@PathVariable("ulIdx") int ulIdx) {
    boardService.deleteBoard(ulIdx);
    return ResponseEntity.ok("삭제 성공");
}
//@PostMapping("/board/delete")
//public String deleteBoard(@RequestParam("ulIdx") int ulIdx) {
//    boardService.deleteBoard(ulIdx);
//    return "redirect:/board"; // 삭제 후 목록 페이지로 이동
//}

}



