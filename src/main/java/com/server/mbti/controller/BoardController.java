package com.server.mbti.controller;

import com.server.mbti.domain.Board;
import com.server.mbti.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class BoardController {

    @Autowired
    private BoardService service;

    @GetMapping("/board")
    public ResponseEntity viewAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.viewAll());
    }
    @PostMapping("/board")
    public ResponseEntity create(Board vo) {
        Board board = service.create(Board.builder()
                .title(vo.getTitle())
                .writer(vo.getWriter())
                .content(vo.getContent())
                .build()
        );
        return ResponseEntity.ok(board);
    }
}
