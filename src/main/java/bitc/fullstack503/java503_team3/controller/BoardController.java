package bitc.fullstack503.java503_team3.controller;

import bitc.fullstack503.java503_team3.dto.BoardDTO;
import bitc.fullstack503.java503_team3.service.BoardService;
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

    @RequestMapping({"/", ""})
    public String index() {
        return "index";
    }

    //  게시물 목록
//  기존의 @RequestMapping 사용방법에서 URI 를 입력했던 부분을 value 속성으로 변경
//  해당 URI와 통신하는 방식을 method 속성을 통해서 지정할 수 있음
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public ModelAndView selectBoardList() throws Exception {
        ModelAndView mav = new ModelAndView("board/boardList");

        List<BoardDTO> boardList = boardService.selectBoardList();
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

    //  @PathVariable : @RequestParam 과 동일한 역할을 하는 어노테이션, REST 방식 사용 시 URI 에 {} 로 지정해 놓은 리소스 값을 받아오는 어노테이션
//  게시물 상세
    @RequestMapping(value = "/board/{idx}", method = RequestMethod.GET)
    public ModelAndView selectBoardDetail(@PathVariable("idx") int idx)  throws Exception {
        ModelAndView mav = new ModelAndView("board/boardDetail");

        BoardDTO board = boardService.selectBoardDetail(idx);
        mav.addObject("board", board);

        return mav;
    }

}
