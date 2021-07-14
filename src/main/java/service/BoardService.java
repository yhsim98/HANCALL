package service;

import domain.Board;

import java.util.List;
import java.util.Map;

public interface BoardService {
    Board createBoard(Board board) throws Exception;
    Map getBoardList(int page);
    Map getBoard(Long boardId) throws Exception;
    void updateBoard(Board board) throws Exception;
    Map searchBoard(String startingPoint, String destination) throws Exception;
    void deleteBoard(Long boardId) throws Exception;
    int getMaxParticipants(Long boardId);
}
