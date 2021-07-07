package service;

import domain.Board;
import domain.Participant;

import java.util.List;

public interface BoardService {
    Board createBoard(Board board);
    List<Board> getBoardList();
    Board getBoard(Long boardId) throws Exception;
    void updateBoard(Board board, Long boardNum) throws Exception;
    List<Board> searchBoard(String startingPoint, String destination);
    void deleteBoard(Long boardId) throws Exception;
}
