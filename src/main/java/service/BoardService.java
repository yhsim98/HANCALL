package service;

import domain.Board;

import java.util.List;

public interface BoardService {
    void createBoard(Board board);
    List<Board> getBoardList();
    Board getBoard(Long BoardId);
    void updateBoard(Board board);
    Board searchBoard(Board board);
}
