package repository;

import domain.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardMapper {
    void insertBoard(Board board);
    List<Board> getBoardList();
    Board getBoardById(Long boardId);
    void updateBoard(Board board);
    Board getBoardListByDestination(Board board);
    Board getBoardListByStartingPoint(Board board);
    Board getBoardListBySPAndDes(Board board);
}
