package com.server.mbti.repo;

import com.server.mbti.domain.Board;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface boardDAO extends JpaRepository<Board, Integer>, QuerydslPredicateExecutor<Board> {

    @Query(value="SELECT * FROM board WHERE mbti_type = :mbtiType", nativeQuery = true)
    String findMBTI(@Param("mbtiType") String mbtiType);

    @Modifying
    @Transactional
    @Query(value="UPDATE board SET count = count + 1 WHERE no = :no", nativeQuery = true)
    void updateCount(@Param("no") int no);
}

