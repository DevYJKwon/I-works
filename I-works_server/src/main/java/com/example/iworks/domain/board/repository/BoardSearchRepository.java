package com.example.iworks.domain.board.repository;

import com.example.iworks.domain.board.dto.request.SearchKeyword;
import com.example.iworks.domain.board.dto.response.ResponseBoard;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BoardSearchRepository {

    //키워드별 게시글 검색
    List<ResponseBoard> findAllByKeyword(Pageable pageable, SearchKeyword keyword);

    //통합 키워드별 게시글 검색
    List<ResponseBoard> findAllByKeywords(Pageable pageable, String keywords);

}
