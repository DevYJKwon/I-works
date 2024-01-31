package com.example.iworks.domain.comment.service;


import com.example.iworks.domain.comment.domain.Comment;
import com.example.iworks.domain.comment.dto.reqeuest.RequestComment;
import com.example.iworks.domain.comment.dto.reqeuest.UpdateComment;
import com.example.iworks.domain.comment.dto.response.ResponseComment;

import java.util.List;

public interface CommentService {

    //댓글 등록
    public void registerComment(RequestComment requestComment);

    //댓글 수정
    public void updateComment(int commentId, UpdateComment updateComment);

    //댓글 삭제
    public void deleteComment(int commentId);

    //게시글별 댓글 검색
    public List<ResponseComment> getAllByBoardId(int boardId);

}
