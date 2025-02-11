package bitc.fullstack503.java503_team3.controller;

import bitc.fullstack503.java503_team3.dto.BoardDTO;
import bitc.fullstack503.java503_team3.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/board/write")
    public String insertBoard(){
        return "board/boardWrite";
    }

    @PostMapping("/board/write")
    public String insertBoard(BoardDTO board)throws Exception{
        boardService.insertBoard(board);
        return "redirect:/board";
    }
}
