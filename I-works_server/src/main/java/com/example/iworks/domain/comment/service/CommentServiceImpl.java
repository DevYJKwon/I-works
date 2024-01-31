package com.example.iworks.domain.comment.service;

import com.example.iworks.domain.board.domain.Board;
import com.example.iworks.domain.board.repository.BoardRepository;
import com.example.iworks.domain.comment.domain.Comment;
import com.example.iworks.domain.comment.dto.reqeuest.RequestComment;
import com.example.iworks.domain.comment.dto.reqeuest.UpdateComment;
import com.example.iworks.domain.comment.dto.response.ResponseComment;
import com.example.iworks.domain.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    private final PageRequest pageRequest = PageRequest.of(0, 10);

    @Override
    public void registerComment(RequestComment requestComment) {
        commentRepository.save(requestComment.toEntity());
    }

    @Override
    public void updateComment(int commentId, UpdateComment updateComment) {
        Comment findComment = commentRepository.findById((long) commentId)
                .orElseThrow(IllegalStateException::new);
        findComment.update(updateComment);
    }

    @Override
    public void deleteComment(int commentId) {
        Comment findComment = commentRepository.findById((long) commentId)
                .orElseThrow(IllegalStateException::new);
        commentRepository.delete(findComment);
    }

    @Override
    public List<ResponseComment> getAllByBoardId(int boardId) {
        Board findBoard = boardRepository.findById((long) boardId)
                .orElseThrow(IllegalStateException::new);
        return commentRepository.findAllByBoard(pageRequest, findBoard);
    }

}
