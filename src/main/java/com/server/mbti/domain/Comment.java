package com.server.mbti.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity @Builder
@Data
@NoArgsConstructor @AllArgsConstructor
@DynamicInsert
public class Comment {

    @Id
    @Column(name="comment_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentCode;

    @Column(name="comment_writer")
    private String commentWriter;

    @Column(name="comment_content")
    private String commentContent;

    @Column(name="comment_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd HH:mm")
    @DateTimeFormat(pattern = "MM-dd HH:mm")
    private LocalDateTime commentDate = LocalDateTime.now();

    @Column
    private int no;

    @Column(name="parent_code")
    private int parentCode;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "parent_code", referencedColumnName = "comment_code", insertable = false, updatable = false)
    private Comment parent;

    @Column(name="delete_code")
    private boolean deleteCode;
}
