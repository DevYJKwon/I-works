package com.example.iworks.domain.comment.repository;

import com.example.iworks.domain.board.domain.Board;
import com.example.iworks.domain.comment.domain.Comment;
import com.example.iworks.domain.comment.domain.QComment;
import com.example.iworks.domain.comment.dto.response.ResponseComment;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.iworks.domain.comment.domain.QComment.comment;


@RequiredArgsConstructor
@Repository
public class CommentRepositoryImpl implements CommentSearchRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ResponseComment> findAllByBoard(Pageable pageable, Board board) {
        return queryFactory
                .selectFrom(comment)
                .where(comment.board.eq(board))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .stream()
                .map(ResponseComment::new)
                .collect(Collectors.toList());
    }
}
