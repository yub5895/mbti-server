package com.server.mbti.controller;

import com.server.mbti.domain.Comment;
import com.server.mbti.domain.CommentDTO;
import com.server.mbti.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class CommentController {

    @Autowired
    private CommentService service;


    @PostMapping("/comment")
    public ResponseEntity add( @RequestBody Comment vo) {
        log.info("commentAdd : " + vo);
        return ResponseEntity.ok(service.create(vo));
    }

    @GetMapping("/board/{no}/comment")
    public ResponseEntity viewAll() {
        List<Comment> comments = service.viewAll();
        List<CommentDTO> response = commentList(comments);
        return ResponseEntity.ok(response);
    }

    public List<CommentDTO> commentList(List<Comment> comments) {
        List<CommentDTO> response = new ArrayList<>();

        for(Comment comment : comments) {
            List<Comment> replies = service.viewReComments(comment.getCommentCode());
            List<CommentDTO> repliesDTO = commentList(replies);
            CommentDTO dto = commentDTO(comment);
            dto.setReplies(repliesDTO);
            response.add(dto);
        }

        return response;
    }

    public CommentDTO commentDTO(Comment comment) {
        return CommentDTO.builder()
                .commentCode(comment.getCommentCode())
                .commentContent(comment.getCommentContent())
                .commentWriter(comment.getCommentWriter())
                .commentDate(comment.getCommentDate())
                .no(comment.getNo())
                .deleteCode(comment.isDeleteCode())
                .build();
    }
}
