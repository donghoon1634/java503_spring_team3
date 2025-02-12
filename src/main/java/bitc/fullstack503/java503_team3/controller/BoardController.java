package bitc.fullstack503.java503_team3.controller;

import bitc.fullstack503.java503_team3.dto.BoardDTO;
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
    private BoardService boardService;

    @Autowired
    private UlCommentService ulCommentService;

    //  게시물 목록
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public ModelAndView selectBoardList() throws Exception {
        ModelAndView mav = new ModelAndView("board/boardList");

        List<BoardDTO> boardList = boardService.selectBoardList();
        mav.addObject("boardList", boardList);

        return mav;
    }
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
    public String insertBoard(BoardDTO board, MultipartHttpServletRequest multipart, HttpServletRequest request)throws Exception{
        HttpSession session = request.getSession();
//        if (session.getAttribute("id") == null) {
//            return "redirect:/login"; // 로그인하지 않은 겨우
//        }
//        else {
//            boardService.insertBoard(board);
//            return "redirect:/board";
//        }
        boardService.insertBoard(board);
        return "redirect:/board";
    }

    // 상세보기
    @GetMapping("/board/{idx}")
    public ModelAndView selectBoard(@PathVariable int idx) throws Exception {
        ModelAndView mav = new ModelAndView("board/boardDetail");
        BoardDTO board = boardService.selectBoardDetail(idx);


        List<UserlifeCommentDTO> comments = ulCommentService.getCommentsByBoardIdx(idx);

        mav.addObject("board", board);
        mav.addObject("comments", comments); // 게시물 상세보기에서 댓글 정보를 가져오기위해서
        return mav;
    }
}
