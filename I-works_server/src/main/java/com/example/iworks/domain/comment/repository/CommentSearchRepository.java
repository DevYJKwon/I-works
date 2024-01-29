package com.example.iworks.domain.comment.repository;

import com.example.iworks.domain.board.domain.Board;

import com.example.iworks.domain.comment.dto.response.ResponseComment;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CommentSearchRepository {

    //게시판별 댓글 검색
    List<ResponseComment> findAllByBoard(Pageable pageable, Board board);
}
