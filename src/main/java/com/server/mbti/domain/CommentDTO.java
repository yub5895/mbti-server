package com.server.mbti.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class CommentDTO {
    private int commentCode;
    private String commentContent;
    private String commentWriter;
    private LocalDateTime commentDate;
    private int no;
    private boolean deleteCode;
    private List<CommentDTO> replies = new ArrayList<>();


}
