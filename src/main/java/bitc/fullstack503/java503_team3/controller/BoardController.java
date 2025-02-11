package bitc.fullstack503.java503_team3.controller;

import bitc.fullstack503.java503_team3.dto.BoardDTO;
import bitc.fullstack503.java503_team3.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.security.Principal;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;
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
}
