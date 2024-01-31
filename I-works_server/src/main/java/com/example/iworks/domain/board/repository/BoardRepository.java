package com.example.iworks.domain.board.repository;

import com.example.iworks.domain.board.domain.Board;
import com.example.iworks.global.model.entity.Code;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearchRepository {

    //카테고리별 게시글 전체 조회
    @Query("select b from Board b order by b.boardUpdatedAt desc")
    List<Board> findAllByBoardCategoryCodeAndBoardOwnerId(Pageable pageable, Code boardCategoryCode, int boardOwnerId);

    //카테고리별 게시글 세부 조회
    @Query("select b from Board b where b.boardId = :boardId order by b.boardUpdatedAt desc")
    Board findByBoardIdAndBoardCategoryCodeAndBoardOwnerIdAndBoardId(int boardId, Code boardCategoryCode, int boardOwnerId);
}
