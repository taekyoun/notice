package com.train.notice.domain.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.train.notice.domain.board.entity_dto.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{


}
