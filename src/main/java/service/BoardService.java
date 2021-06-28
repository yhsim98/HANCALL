package service;

import domain.Board;

import java.util.List;

public interface BoardService {
    Board createBoard(Board board);
    List<Board> getBoardList();
    Board getBoard(Long BoardId);
    void updateBoard(Board board, Long boardNum, String token);
    Board searchBoard(Board board);
    void deleteBoard(Long BoardId, String token);
}
