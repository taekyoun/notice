package com.train.notice.domain.board.entity_dto;

import java.time.LocalDateTime;

import com.train.notice.domain.member.entity_dto.Member;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardDto {

        private Long id;
        private String name;
        private String content;
        private LocalDateTime createTime;
        private Member writer;

        public static BoardDto from (Board board){
                return BoardDto.builder()
                        .id(board.getId())
                        .name(board.getName())
                        .content(board.getContent())
                        .createTime(board.getCreateTime())
                        .writer(board.getWriter())
                        .build();
        }
}
