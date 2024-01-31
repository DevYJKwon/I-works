package com.example.iworks.domain.comment.dto.response;

import com.example.iworks.domain.board.domain.Board;
import com.example.iworks.domain.comment.domain.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseComment {

    private int commentId; // 댓글 아이디
    private Board board; // 게시글
    private int parentCommentId; // 부모 댓글 아이디
    private int commentCreatorId; // 댓글 작성자 아이디
    private String commentContent; // 댓글 내용
    private int commentOrder; // 댓글 순서
    private int commentDepth; // 계층
    private LocalDateTime commentCreatedAt; // 댓글 작성일시
    private LocalDateTime commentUpdatedAt; // 댓글 수정일시
    private LocalDateTime commentDeletedAt; // 댓글 삭제일시

    public ResponseComment(Comment comment) {
        this.commentId = comment.getCommentId();
        this.board = comment.getBoard();
        this.parentCommentId = comment.getParentCommentId();
        this.commentCreatorId = comment.getCommentCreatorId();
        this.commentContent = comment.getCommentContent();
        this.commentOrder = comment.getCommentOrder();
        this.commentDepth = comment.getCommentDepth();
        this.commentCreatedAt = comment.getCommentCreatedAt();
        this.commentUpdatedAt = comment.getCommentUpdatedAt();
        this.commentDeletedAt = comment.getCommentDeletedAt();
    }

}
