package com.train.notice.domain.board.entity_dto;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "notice_board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;
    
    @Column
    private String name;

    @Column
    private String content;

    @Column
    private LocalDateTime createTime;

    @Column
    private String poster;


    public static Board create(String name,String content,LocalDateTime createTime,String poster){
        Board board = new Board();
        board.name = name;
        board.content = content;
        board.createTime = createTime;
        board.poster = poster;
        return board;
    }

    public void update(String name, String content){
        this.name = name;
        this.content = content;
    }

}
