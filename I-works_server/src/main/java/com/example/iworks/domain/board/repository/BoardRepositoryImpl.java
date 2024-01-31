package com.example.iworks.domain.board.repository;

import com.example.iworks.domain.board.dto.request.SearchKeyword;
import com.example.iworks.domain.board.dto.response.ResponseBoard;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.iworks.domain.board.domain.QBoard.board;

@RequiredArgsConstructor
@Repository
public class BoardRepositoryImpl implements BoardSearchRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ResponseBoard> findAllByKeyword(Pageable pageable, SearchKeyword keyword) {
        return queryFactory
                .selectFrom(board)
                .where(
                        eqBoardCreatorId(keyword.getBoardCreatorId())
                                .or(eqBoardTitle(keyword.getBoardTitle()))
                                .or(eqBoardContent(keyword.getBoardContent()))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .stream()
                .filter(Objects::nonNull)
                .map(ResponseBoard::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResponseBoard> findAllByKeywords(Pageable pageable, String keywords) {
        return queryFactory
                .selectFrom(board)
                .where(
                        eqBoardTitle(keywords)
                                .or(eqBoardContent(keywords))
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch()
                .stream()
                .map(ResponseBoard::new)
                .collect(Collectors.toList());
    }

    private BooleanExpression eqBoardCreatorId(int boardCreatorId) {
        return board.boardCreatorId.eq(boardCreatorId);
    }

    private BooleanExpression eqBoardTitle(String boardTitle) {
        return StringUtils.hasText(boardTitle) ? board.boardTitle.eq(boardTitle) : null;
    }

    private BooleanExpression eqBoardContent(String boardContent) {
        return StringUtils.hasText(boardContent) ? board.boardContent.eq(boardContent) : null;
    }

}
