package com.server.mbti.service;

import com.server.mbti.domain.Board;
import com.server.mbti.repo.boardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private boardDAO dao;

    public List<Board> viewAll() {
        return dao.findAll();
    }

    public List<Board> findMbti() {
        return findMbti();
    }

    public Board create(Board vo) {
        return dao.save(vo);
    }


}
