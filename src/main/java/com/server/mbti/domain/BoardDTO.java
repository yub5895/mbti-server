package com.server.mbti.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Builder @Data
@NoArgsConstructor @AllArgsConstructor
public class BoardDTO {

    private MultipartFile boardFile;
    private String title;
    private String writer;
    private String content;
    private String mbtiType;
    private String url;
    private LocalDateTime writeDate = LocalDateTime.now();
}
