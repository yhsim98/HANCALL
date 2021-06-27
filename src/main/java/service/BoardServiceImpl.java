package service;

import domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BoardMapper;

import java.util.List;

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

    @Override
    public List<Board> getBoardList() {
        return boardMapper.getBoardList();
    }

    @Override
    public Board getBoard(Long boardId) {
        return boardMapper.getBoardById(boardId);
    }

    @Override
    public void updateBoard(Board board) {
        boardMapper.updateBoard(board);
    }

    @Override
    public Board searchBoard(Board board) {
        if(board.getDestination() == null){
            return boardMapper.getBoardListByStartingPoint(board);
        }
        else if(board.getStartingPoint() == null){
            return boardMapper.getBoardListByStartingPoint(board);
        }
        else{
            return boardMapper.getBoardListBySPAndDes(board);
        }
    }
}
