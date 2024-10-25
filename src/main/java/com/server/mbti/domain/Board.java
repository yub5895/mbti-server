package com.server.mbti.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Entity @Builder
@Data @NoArgsConstructor @AllArgsConstructor
@DynamicInsert
public class Board {

    @Id
    private int no;

    @Column
    private String title;

    @Column
    private String writer;

    @Column
    private String content;

    @Column
    private String url;

    @Column
    private int count;

    @Column(name="mbti_type")
    private String mbtiType;

    @Column(name="write_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd HH:mm")
    @DateTimeFormat(pattern = "MM-dd HH:mm")
    private LocalDateTime writeDate = LocalDateTime.now();


}
