package com.minho.blog.controller;

import com.minho.blog.config.auth.PrincipalDetail;
import com.minho.blog.model.Board;
import com.minho.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping({"", "/"})
    public String index(Model model){
        model.addAttribute("boards", boardService.글목록());
        return "index";
        //컨트롤러라서 viewResolver 라는 애가 index 로 갈 때 model 을 들고감, prefix suffix 도 붙여주고
    }

    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model){
        model.addAttribute("board", boardService.글상세보기(id));
        return "board/detail";
    }
}
