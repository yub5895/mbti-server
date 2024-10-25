package com.server.mbti.service;

import com.querydsl.core.BooleanBuilder;
import com.server.mbti.domain.Board;
import com.server.mbti.repo.boardDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private boardDAO dao;

    public Page<Board> viewAll(BooleanBuilder builder, Pageable pageable) {
        return dao.findAll(builder, pageable);
    }

    public Board create(Board vo) {
        return dao.save(vo);
    }

    public String findMBTI(String mbtiType) {
        return dao.findMBTI(mbtiType);
    }

    public Board view(int no) {
        dao.updateCount(no);
        return dao.findById(no).get();
    }
}
