package bitc.fullstack503.java503_team3.controller;

import bitc.fullstack503.java503_team3.dto.UserlifeDTO;
import bitc.fullstack503.java503_team3.dto.UserlifeCommentDTO;
import bitc.fullstack503.java503_team3.service.BoardService;
import bitc.fullstack503.java503_team3.service.UlCommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private UlCommentService ulCommentService;

    @Autowired
    private BoardService boardService;

    //  게시물 목록
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public ModelAndView selectBoardList() throws Exception {
        ModelAndView mav = new ModelAndView("board/boardList");
        List<UserlifeDTO> boardList = boardService.selectBoardList();
        mav.addObject("boardList", boardList);
        return mav;
    }

    //    게시글 쓰기
    // 작성 화면
    @GetMapping("/board/write")
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

    @PostMapping("/board/write")
    public String insertBoard(UserlifeDTO ul, MultipartHttpServletRequest multipart, HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
//        if (session.getAttribute("id") == null) {
//            return "redirect:/login"; // 로그인하지 않은 겨우
//        }
//        else {
//            boardService.insertBoard(ul);
//            return "redirect:/board";
//        }
        boardService.insertBoard(ul);
        return "redirect:/board";
    }

    //  게시물 상세
    @RequestMapping(value = "/board/{ulIdx}", method = RequestMethod.GET)
    public ModelAndView selectBoardDetail(@PathVariable("ulIdx") int ulIdx)  throws Exception {
        ModelAndView mav = new ModelAndView("board/boardDetail");
        UserlifeDTO ul = boardService.selectBoardDetail(ulIdx);

        // 게시물 번호에 해당하는 댓글 목록 가져오기
//        List<UserlifeCommentDTO> ulcomment = ulCommentService.getUlCommentByUlIdx(ulIdx);
        List<UserlifeCommentDTO> ulcomment = ulCommentService.getCommentsByPage(ulIdx,0,5);
        mav.addObject("ul", ul);
        // ulcomment는 댓글정보
        mav.addObject("ulcomment", ulcomment);
        return mav;
    }

    //    게시물 수정
    @PutMapping("/board/{ulIdx}")
    public String updateBoard(UserlifeDTO ul) throws Exception {
        boardService.updateBoard(ul);
        return "redirect:/board/boardList";
    }

    //    게시물 삭제
    @DeleteMapping("/board/{ulIdx}")
    public String deleteBoard(@PathVariable("ulIdx") int ulIdx) throws Exception {
        boardService.deleteBoard(ulIdx);
        return "redirect:/board/boardList";
    }


}
