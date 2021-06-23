package service;

import domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper){
        this.boardMapper = boardMapper;
    }

    @Override
    public void createBoard(Board board) {
        boardMapper.insertBoard(board);
    }
}
