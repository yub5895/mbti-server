package com.server.mbti.repo;

import com.server.mbti.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentDAO extends JpaRepository<Comment, Integer> {

}
