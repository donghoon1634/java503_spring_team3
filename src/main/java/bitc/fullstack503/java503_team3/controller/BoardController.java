package bitc.fullstack503.java503_team3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
    @RequestMapping({"/", ""})
    public String index() {
        return "index";
    }

    //  게시물 목록
//  기존의 @RequestMapping 사용방법에서 URI 를 입력했던 부분을 value 속성으로 변경
//  해당 URI와 통신하는 방식을 method 속성을 통해서 지정할 수 있음


    //  @PathVariable : @RequestParam 과 동일한 역할을 하는 어노테이션, REST 방식 사용 시 URI 에 {} 로 지정해 놓은 리소스 값을 받아오는 어노테이션
//  게시물 상세


    //  @GetMapping : 클라이언트에서 데이터 전송 방식을 GET 으로 설정한 URL만 접속
//  @RequestMapping(method = RequestMethod.GET) 과 동일한 방식
//  게시물 등록 화면
    @GetMapping("/board/write")
    public String insertBoard() {
        return "board/boardWrite";
    }
    //  @PostMapping : 클라이언트에서 데이터 전송 방식을 POST 로 설정한 URL만 접속
//  @RequestMapping(method = RequestMethod.POST) 과 동일한 방식
//  게시물 등록 처리
    @PostMapping("/board/write")
    public String insertBoard(BoardDTO board) throws Exception {
        boardService.insertBoard(board);

        return "redirect:/board";
    }

}
