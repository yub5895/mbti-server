package com.server.mbti.controller;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.server.mbti.domain.Board;
import com.server.mbti.domain.BoardDTO;
import com.server.mbti.domain.QBoard;
import com.server.mbti.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class BoardController {

    @Autowired
    private BoardService service;

    private String path = "\\\\192.168.10.51\\youtube\\";

    // 게시글 조회
    @GetMapping("/board")
    public ResponseEntity viewAll(@RequestParam(name="keyword", required = false) String keyword, @RequestParam(name="page", defaultValue = "1") int page, @RequestParam(name="mbti", required = false) String mbti) {

        Sort sort = Sort.by("no").descending();
        Pageable pageable = PageRequest.of(page-1, 20, sort);

        BooleanBuilder builder = new BooleanBuilder();

        QBoard qBoard = QBoard.board;

        if(keyword!=null && !keyword.equals("")) {
            BooleanExpression expression = qBoard.title.like("%" + keyword + "%");

            builder.and(expression);
        }

        if (mbti != null && !mbti.isEmpty()) {
            BooleanExpression mbtiExpression = qBoard.mbtiType.eq(mbti);
            builder.and(mbtiExpression);
        }

        Page<Board> list = service.viewAll(builder, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    // 게시글 추가
    @PostMapping("/board")
    public ResponseEntity<Board> create(BoardDTO dto) throws IOException {
        if (dto == null || dto.getTitle() == null || dto.getWriter() == null || dto.getContent() == null || dto.getMbtiType() == null) {
            return ResponseEntity.badRequest().body(null);
        }


        if(dto.getBoardFile() != null && !dto.getBoardFile().isEmpty()) {
            String uuid = UUID.randomUUID().toString();

            String fileName = uuid + "_" + dto.getBoardFile().getOriginalFilename();
            File boardFile = new File(path + "thumbnail" + File.separator + fileName);
            dto.getBoardFile().transferTo(boardFile);
            dto.setUrl("http://192.168.10.51:8082/thumbnail/" + File.separator + fileName);
        }



        Board board = service.create(Board.builder()
                        .url(dto.getUrl())
                        .title(dto.getTitle())
                        .writer(dto.getWriter())
                        .content(dto.getContent())
                        .writeDate(dto.getWriteDate())
                        .mbtiType(dto.getMbtiType())
                .build());
        return ResponseEntity.ok(board);


    }
//      vo.setWriteDate(LocalDateTime.now());

    // mbtiType 조회
    @GetMapping("/mbti/{mbtiType}")
    public ResponseEntity findMBTI(@PathVariable(name="mbtiType") String mbtiType) {
        log.info("mbti : " + mbtiType);
        return ResponseEntity.status(HttpStatus.OK).body(service.findMBTI(mbtiType));
    }

    @GetMapping("/board/{no}")
    public ResponseEntity view(@PathVariable(name="no") int no) {
        log.info("no: " + no);
        return ResponseEntity.ok(service.view(no));
    }
}
