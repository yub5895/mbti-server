package com.server.mbti.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.mbti.domain.Comment;
import com.server.mbti.domain.QComment;
import com.server.mbti.repo.CommentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentDAO dao;

    @Autowired
    private JPAQueryFactory queryFactory;

    private final QComment qComment = QComment.comment;

    // 댓글 추가
    public Comment create(Comment vo) {
        return dao.save(vo);
    }

    public List<Comment> viewAll() {
        return queryFactory
                .selectFrom(qComment)
                .where(qComment.parentCode.eq(0))
                .orderBy(qComment.commentDate.asc())
                .fetch();
    }

    public List<Comment> viewReComments(int parentCode) {
        return queryFactory
                .selectFrom(qComment)
                .where(qComment.parentCode.eq(parentCode))
                .orderBy(qComment.commentDate.asc())
                .fetch();
    }

}
