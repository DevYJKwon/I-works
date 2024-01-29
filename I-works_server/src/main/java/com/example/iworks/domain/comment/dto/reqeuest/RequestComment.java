package com.example.iworks.domain.comment.dto.reqeuest;

import com.example.iworks.domain.comment.domain.Comment;
import lombok.Getter;

@Getter
public class RequestComment {
    private int commentBoardId; //게시글 아이디
    private int parentCommentId; //부모 댓글 아이디
    private int commentCreatorId; //댓글 작성자 아이디
    private String commentContent; //댓글 내용
    private int commentOrder; //댓글 순서
    private int commentDepth; // 계층

    public Comment toEntity() {
        return Comment.builder()
                .parentCommentId(parentCommentId)
                .commentCreatorId(commentCreatorId)
                .commentContent(commentContent)
                .commentOrder(commentOrder)
                .commentDepth(commentDepth)
                .build();
    }
}
