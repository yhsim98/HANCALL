package service;

import domain.Board;
import domain.Participant;
import exception.ConflictException;

import java.util.List;

public interface BoardService {
    Board createBoard(Board board) throws Exception;
    List<Board> getBoardList();
    Board getBoard(Long boardId) throws Exception;
    void updateBoard(Board board, Long boardNum) throws Exception;
    List<Board> searchBoard(String startingPoint, String destination) throws Exception;
    void deleteBoard(Long boardId) throws Exception;
    int getMaxParticipants(Long boardId);
}
