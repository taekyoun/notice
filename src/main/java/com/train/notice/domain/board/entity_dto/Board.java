package com.train.notice.domain.board.entity_dto;

import java.time.LocalDateTime;

import com.train.notice.domain.member.entity_dto.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private LocalDateTime createTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="member_id", nullable = false)
    private Member writer;


    public static Board create(String name,String content,LocalDateTime createTime,Member writer){
        Board board = new Board();
        board.name = name;
        board.content = content;
        board.createTime = createTime;
        board.writer = writer;
        return board;
    }

    public void update(String name, String content){
        this.name = name;
        this.content = content;
    }

}
