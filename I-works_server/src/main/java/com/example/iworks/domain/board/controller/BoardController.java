package com.example.iworks.domain.board.controller;

import com.example.iworks.domain.board.dto.request.RequestBoard;
import com.example.iworks.domain.board.dto.request.SearchKeyword;
import com.example.iworks.domain.board.dto.request.UpdateBoard;
import com.example.iworks.domain.board.service.BoardService;
import com.example.iworks.global.model.Response;
import com.example.iworks.global.model.entity.Code;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final Response response;

    //게시글 등록
    @PostMapping("/")
    public ResponseEntity<?> createBoard(@RequestBody RequestBoard requestBoard) {
        boardService.registerBoard(requestBoard);
        return response.handleSuccess("게시글 등록 완료");
    }

    //게시글 수정
    @PutMapping("/{boardId}")
    public ResponseEntity<?> updateBoard(@PathVariable int boardId, @RequestBody UpdateBoard updateBoard) {
        boardService.updateBoard(boardId, updateBoard);
        return response.handleSuccess("게시글 수정 완료");
    }

    //게시글 삭제
    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoard(@PathVariable int boardId) {
        boardService.deleteBoard(boardId);
        return response.handleSuccess("게시글 삭제 완료");
    }

    //게시글 전체 조회
    @GetMapping("/")
    public ResponseEntity<?> getBoards() {
        return response.handleSuccess(boardService.getAll());
    }

    //카테고리별 게시글 조회 (공지, 자유)
    @GetMapping("/byCategory")
    public ResponseEntity<?> getBoardsByCategoryCode(@RequestParam int boardCategoryCodeId) {
        return response.handleSuccess(boardService.getAllByBoardCategoryCode(boardCategoryCodeId));
    }

    //카테고리별 게시글 조회 (부서, 팀)
    @GetMapping("byCategoryAndOwner")
    public ResponseEntity<?> getBoardsByCategoryCodeAndOwnerId(
            @RequestParam int boardCategoryCodeId,
            @RequestParam int boardOwnerId) {
        return response.handleSuccess(boardService.getAllByBoardCategoryCodeAndBoardOwnerId(boardCategoryCodeId, boardOwnerId));
    }

    //키워드별 게시글 검색
    @GetMapping("/search")
    public ResponseEntity<?> getBoardsByKeyword(@RequestBody SearchKeyword keyword) {
        return response.handleSuccess(boardService.getAllByKeyword(keyword));
    }

    //통합 키워드별 게시글 검색
    @GetMapping("/search")
    public ResponseEntity<?> getBoardsByKeywords(@RequestParam String keywords) {
        return response.handleSuccess(boardService.getAllByKeywords(keywords));
    }
    
    //북마크 등록/삭제
    @PostMapping("/bookmark/")
    public ResponseEntity<?> bookmarkBoard(@RequestParam int boardId, @RequestParam String userEid) {
        boardService.bookmarkBoard(boardId, userEid);
        return response.handleSuccess("북마크 완료");
    }

}
