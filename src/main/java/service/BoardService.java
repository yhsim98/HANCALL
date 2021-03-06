package service;

import domain.Board;
import domain.Criteria;

import java.util.List;
import java.util.Map;

public interface BoardService {
    Board createBoard(Board board) throws Exception;
    Map getBoardList(Long lastBoardId);
    Map getBoard(Long boardId) throws Exception;
    void updateBoard(Board board, Long boardId) throws Exception;
    Map searchBoard(String startingPoint, String destination, Long lastBoardId) throws Exception;
    void deleteBoard(Long boardId) throws Exception;
    int getMaxParticipants(Long boardId);
}
