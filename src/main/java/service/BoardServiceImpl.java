package service;

import annotation.Auth;
import domain.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BoardMapper;
import util.JwtUtil;

import java.util.List;


@Service
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;
    private final JwtUtil jwtUtil;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper, JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
        this.boardMapper = boardMapper;
    }


    @Override
    public Board createBoard(Board board) {
        boardMapper.insertBoard(board);
        return boardMapper.getBoardById(board.getBoard_Id());
    }

    @Override
    public List<Board> getBoardList() {
        return boardMapper.getBoardList();
    }

    @Override
    public Board getBoard(Long boardId) {
        return boardMapper.getBoardById(boardId);
    }

    @Auth
    @Override
    public void updateBoard(Board board, Long boardId, String token) {
        Board selectBoard = boardMapper.getBoardById(boardId);
        if(jwtUtil.getUserIdFromJWT(token) != selectBoard.getBoard_Id()){

        }
        board.setBoard_Id(boardId);
        boardMapper.updateBoard(board);
    }

    @Override
    public Board searchBoard(Board board) {
        if(board.getDestination() == null){
            return boardMapper.getBoardListByStartingPoint(board);
        }
        else if(board.getStarting_Point() == null){
            return boardMapper.getBoardListByDestination(board);
        }
        else{
            return boardMapper.getBoardListBySPAndDes(board);
        }
    }


    @Override
    public void deleteBoard(Long boardId, String token) {
        Board selectBoard = boardMapper.getBoardById(boardId);
        if(jwtUtil.getUserIdFromJWT(token) != selectBoard.getBoard_Id()){

        }
        boardMapper.deleteBoard(boardId);
    }
}
