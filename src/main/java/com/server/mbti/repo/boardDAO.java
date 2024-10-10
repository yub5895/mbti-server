package com.server.mbti.repo;

import com.server.mbti.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface boardDAO extends JpaRepository<Board, Integer> {

  /*
  @Query("SELECT p FROM Board p WHERE p.mbtiType = :mbtiType")
    List<Board> findMbti(@Param("mbtiType") String mbtiType);   */
}

