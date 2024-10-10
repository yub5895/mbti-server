package com.server.mbti.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity @Builder
@Data @NoArgsConstructor @AllArgsConstructor
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

    @Column
    private LocalDateTime date;
}
