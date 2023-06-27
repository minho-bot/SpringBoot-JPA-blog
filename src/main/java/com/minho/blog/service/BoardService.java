package com.minho.blog.service;

import com.minho.blog.model.Board;
import com.minho.blog.model.User;
import com.minho.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    public void 글쓰기(Board board, User user) {
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    public List<Board> 글목록(){
        return boardRepository.findAll();
    }
}
