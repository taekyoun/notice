package com.train.notice.domain.board.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.train.notice.domain.board.entity_dto.Board;
import com.train.notice.domain.board.entity_dto.BoardDto;
import com.train.notice.domain.board.repository.BoardRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    
    @Transactional(readOnly = true)
    public Page<BoardDto> readBoardList(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return boardRepository.findAll(pageable).map(BoardDto::from);
    }

    @Transactional(readOnly = true)
    public BoardDto readBoard(Long id){
        return BoardDto.from(boardRepository.findById(id).orElseThrow(()->new EntityNotFoundException()));
    }

    @Transactional
    public BoardDto createBoard(BoardDto boardDto){
        Board board =Board.create(boardDto.getName(), boardDto.getContent(), LocalDateTime.now(), boardDto.getPoster());
        return BoardDto.from(boardRepository.save(board));
    }

    @Transactional
    public BoardDto updateBoard(Long id, BoardDto boardDto){
        Board board = boardRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        board.update(boardDto.getName(),boardDto.getContent());
        return BoardDto.from(boardRepository.save(board));

    }

    @Transactional
    public void deleteBoard(Long id){
        boardRepository.deleteById(id);
    }

}
