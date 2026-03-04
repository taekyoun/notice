package com.train.notice.domain.board.entity_dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BoardDto {

        private Long id;
        private String name;
        private String content;
        private LocalDateTime createTime;
        private String poster;

        public static BoardDto from (Board board){
                return BoardDto.builder()
                        .id(board.getId())
                        .name(board.getName())
                        .content(board.getContent())
                        .createTime(board.getCreateTime())
                        .poster(board.getPoster())
                        .build();
        }
}
