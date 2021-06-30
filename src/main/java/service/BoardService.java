package service;

import domain.Board;

import java.util.List;

public interface BoardService {
    Board createBoard(Board board, String token);
    List<Board> getBoardList();
    Board getBoard(Long BoardId) throws Exception;
    void updateBoard(Board board, Long boardNum, String token) throws Exception;
    List<Board> searchBoard(Board board);
    void deleteBoard(Long BoardId, String token) throws Exception;
}
