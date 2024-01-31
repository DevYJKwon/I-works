package com.example.iworks.domain.board.service;

import com.example.iworks.domain.board.domain.Board;
import com.example.iworks.domain.board.domain.Bookmark;
import com.example.iworks.domain.board.dto.request.RequestBoard;
import com.example.iworks.domain.board.dto.request.SearchKeyword;
import com.example.iworks.domain.board.dto.request.UpdateBoard;
import com.example.iworks.domain.board.dto.response.ResponseBoard;
import com.example.iworks.domain.board.repository.BoardRepository;
import com.example.iworks.domain.board.repository.BookmarkRepository;
import com.example.iworks.domain.user.domain.User;
import com.example.iworks.domain.user.repository.UserRepository;
import com.example.iworks.global.model.entity.Code;
import com.example.iworks.global.model.repository.CodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;
    private final CodeRepository codeRepository;

    private final PageRequest pageRequest = PageRequest.of(0, 10);

    @Transactional
    public void registerBoard(RequestBoard requestBoard) {
        boardRepository.save(requestBoard.toEntity());
    }

    @Transactional
    public void updateBoard(int boardId, UpdateBoard updateBoard) {
        Board findBoard = boardRepository.findById((long) boardId)
                .orElseThrow(IllegalStateException::new); //예외 메서드 추가
        findBoard.update(updateBoard);
    }

    @Transactional
    public void deleteBoard(int boardId) {
        Board findBoard = boardRepository.findById((long) boardId)
                .orElseThrow(IllegalStateException::new);
        findBoard.delete(boardId);
    }

    public List<ResponseBoard> getAll() {
        return boardRepository.findAll(pageRequest)
                .stream()
                .map(ResponseBoard::new)
                .collect(toList());
    }

    @Override
    public ResponseBoard getBoard(int boardId) {
        return boardRepository.findById((long) boardId)
                .map(ResponseBoard::new)
                .orElseThrow(IllegalStateException::new);
    }

    public List<ResponseBoard> getAllByBoardCategoryCodeAndBoardOwnerId(int boardCategoryCodeId, int boardOwnerId) {
        return boardRepository.findAllByBoardCategoryCodeAndBoardOwnerId(pageRequest, findCode(boardCategoryCodeId), boardOwnerId)
                .stream()
                .map(ResponseBoard::new)
                .collect(toList());
    }

    @Override
    public ResponseBoard getBoardByBoardCategoryCodeAndBoardOwnerId(int boardId, int boardCategoryCodeId, int boardOwnerId) {
        return new ResponseBoard(
                boardRepository.findByBoardIdAndBoardCategoryCodeAndBoardOwnerIdAndBoardId(
                        boardId,
                        findCode(boardCategoryCodeId),
                        boardOwnerId));
    }

    public List<ResponseBoard> getAllByKeyword(SearchKeyword keyword) {
        return boardRepository.findAllByKeyword(pageRequest, keyword);
    }

    public List<ResponseBoard> getAllByKeywords(String keywords) {
        return boardRepository.findAllByKeywords(pageRequest, keywords);
    }

    @Transactional
    public void bookmarkBoard(int boardId, String userEid) {
        Board findBoard = boardRepository.findById((long) boardId)
                .orElseThrow(IllegalStateException::new);
        User findUser = userRepository.findByUserEid(userEid);
        if (findUser == null) {
            throw new IllegalStateException("존재하지 않는 유저입니다.");
        }

        Bookmark findBookmark = bookmarkRepository.findBookmarkByBoardAndUser(findBoard, findUser);

        if (findBookmark == null) {
            Bookmark bookmark = Bookmark.builder()
                    .board(findBoard)
                    .user(findUser)
                    .build();
            bookmarkRepository.save(bookmark);
        }
        else {
            bookmarkRepository.delete(findBookmark);
        }
    }

    private Code findCode(int boardCategoryCodeId) {
        return codeRepository.findById(boardCategoryCodeId)
                .orElseThrow(IllegalStateException::new);
    }

}
