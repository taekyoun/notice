package com.train.notice.domain.board.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.train.notice.domain.board.entity_dto.BoardDto;
import com.train.notice.domain.board.service.BoardService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/boards")
    public String getBoards(Model model) {
        Page<BoardDto> boards = boardService.readBoardList(0,10);
        model.addAttribute("boards", boards);
        return "board";
    }
    
    @GetMapping("/boards/new")
    public String newBoard(Model model,Authentication authentication) {
        model.addAttribute("board", BoardDto.builder().build());
        model.addAttribute("mode", "new");
        model.addAttribute("poster", authentication.getName());
        return "board-form";
    }
    @GetMapping("/boards/{id}/edit")
    public String editBoard(@PathVariable Long id, Model model, Authentication authentication) {
        model.addAttribute("board", boardService.readBoard(id));
        model.addAttribute("mode", "edit");
        model.addAttribute("poster", authentication.getName());
        return "board-form";
    }

    @GetMapping("/boards/{id}")
    public String getBoardOne(@PathVariable Long id, Model model) {
        model.addAttribute("board", boardService.readBoard(id));
        return "board-detail";
    }

    @PostMapping("/boards")
    public String createBoard(@ModelAttribute BoardDto boardDto){
        BoardDto board = boardService.createBoard(boardDto);
        System.out.println(board.getId());
        return "/boards/" + board.getId();
    }

    @ResponseBody
    @PutMapping("/boards/{id}")
    public String updateBoard(@PathVariable Long id, @ModelAttribute BoardDto boardDto) {
        boardService.updateBoard(id, boardDto);
        return "/boards/" + id;
    }
    
    @ResponseBody
    @DeleteMapping("/boards/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }

}
