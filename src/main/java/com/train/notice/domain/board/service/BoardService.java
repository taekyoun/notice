package com.train.notice.domain.board.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.train.notice.domain.board.entity_dto.Board;
import com.train.notice.domain.board.entity_dto.BoardDto;
import com.train.notice.domain.board.repository.BoardRepository;
import com.train.notice.domain.member.entity_dto.Member;
import com.train.notice.domain.member.repository.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    
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
    public Long createBoard(BoardDto boardDto, String loginId){
        Member member = memberRepository.findById(loginId).orElseThrow(()->new IllegalArgumentException("회원이 존재하지 않습니다."));

        Board board =Board.create(boardDto.getName(), boardDto.getContent(), LocalDateTime.now(),member);
        return boardRepository.save(board).getId();
    }

    @Transactional
    public BoardDto updateBoard(Long id, BoardDto boardDto){
        Board board = boardRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        board.update(boardDto.getName(),boardDto.getContent());
        return BoardDto.from(boardRepository.save(board));

    }

    @Transactional
    public void deleteBoard(Long id, String loginId){
        Board board = boardRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        Member loginMember = memberRepository.findById(loginId).orElseThrow(()-> new IllegalArgumentException("회원이 존재하지 않습니다"));

        boolean isWriter = board.getWriter().getUsername().equals(loginMember.getUsername());
        if(!isWriter){
            throw new AccessDeniedException("삭제 권한이 없습니다.");
        }
        boardRepository.deleteById(id);
    }

}
