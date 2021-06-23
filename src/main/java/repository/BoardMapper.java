package repository;

import domain.Board;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardMapper {
    void insertBoard(Board board);
}
