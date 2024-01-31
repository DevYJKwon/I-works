package com.example.iworks.domain.board.service;

import com.example.iworks.domain.board.domain.Board;
import com.example.iworks.domain.board.dto.request.RequestBoard;
import com.example.iworks.domain.board.dto.request.SearchKeyword;
import com.example.iworks.domain.board.dto.request.UpdateBoard;
import com.example.iworks.domain.board.dto.response.ResponseBoard;

import java.util.List;


public interface BoardService {

    //게시글 등록
    public void registerBoard(RequestBoard requestBoard);

    //게시글 수정
    public void updateBoard(int boardId, UpdateBoard updateBoard);

    //게시글 삭제
    public void deleteBoard(int boardId);

    //게시글 전체 조회
    public List<ResponseBoard> getAll();

    //게시글 세부 조회
    public ResponseBoard getBoard(int boardId);

    //카테고리별 게시글 전체 조회
    public List<ResponseBoard> getAllByBoardCategoryCodeAndBoardOwnerId(int boardCategoryCodeId, int boardOwnerId);

    //카테고리별 게시글 세부 조회
    public ResponseBoard getBoardByBoardCategoryCodeAndBoardOwnerId(int boardId, int boardCategoryCodeId, int boardOwnerId);

    //키워드별 게시글 검색
    public List<ResponseBoard> getAllByKeyword(SearchKeyword keyword);

    //통합 키워드별 게시글 검색
    public List<ResponseBoard> getAllByKeywords(String keywords);

    //게시글 북마크 등록/삭제
    public void bookmarkBoard(int boardId, String userEid);

}
