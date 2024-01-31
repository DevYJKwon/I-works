package com.example.iworks.domain.comment.controller;

import com.example.iworks.domain.comment.dto.reqeuest.RequestComment;
import com.example.iworks.domain.comment.dto.reqeuest.UpdateComment;
import com.example.iworks.domain.comment.service.CommentService;
import com.example.iworks.global.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final Response response;

    //댓글 등록
    @PostMapping("/")
    public ResponseEntity<?> createComment(@RequestBody RequestComment requestComment) {
        commentService.registerComment(requestComment);
        return response.handleSuccess("댓글 등록 완료");
    }

    //댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable int commentId, @RequestBody UpdateComment updateComment) {
        commentService.updateComment(commentId, updateComment);
        return response.handleSuccess("댓글 수정 완료");
    }

    //댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable int commentId) {
        commentService.deleteComment(commentId);
        return response.handleSuccess("댓글 삭제 완료");
    }
    
    //게시글별 댓글 검색
    @GetMapping("/{boardId}")
    public ResponseEntity<?> getCommentsByBoardId(@PathVariable int boardId) {
        return response.handleSuccess(commentService.getAllByBoardId(boardId));
    }

}
