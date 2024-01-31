package com.example.iworks.domain.board.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchKeyword {

    private int boardCreatorId; // 작성자 아이디
    private String boardTitle; // 제목
    private String boardContent; // 내용

}
