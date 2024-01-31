package com.example.iworks.domain.board.dto.request;

import com.example.iworks.domain.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateBoard {

    private int boardId;
    private int boardModifierId; // 수정자 아이디
    private String boardTitle; // 제목
    private String boardContent; // 내용

    public Board toEntity() {
        return Board.builder()
                .boardId(boardId)
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardModifierId(boardModifierId)
                .build();
    }

}
