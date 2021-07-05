package service;


import domain.Board;
import exception.NotFoundException;
import exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import repository.BoardMapper;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
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
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");

        board.setWriter_Id(jwtUtil.getUserIdFromJWT(token));
        boardMapper.insertBoard(board);

        return boardMapper.getBoardById(board.getBoard_Id());
    }

    @Override
    public List<Board> getBoardList() {
        return boardMapper.getBoardList();
    }


    @Override
    public Board getBoard(Long boardId) throws Exception{
        Board select = boardMapper.getBoardById(boardId);

        if(select == null) throw new NotFoundException("존재하지 않는 게시판입니다");

        return boardMapper.getBoardById(boardId);
    }


    @Override
    public void updateBoard(Board board, Long boardId) throws Exception{
        Board selectBoard = boardMapper.getBoardById(boardId);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");

        if(selectBoard == null){
            throw new NotFoundException("존재하지 않는 게시판입니다");
        }

        if(!jwtUtil.getUserIdFromJWT(token).equals(selectBoard.getBoard_Id())){
            throw new UnauthorizedException("권한이 없습니다");
        }

        board.setBoard_Id(boardId);
        boardMapper.updateBoard(board);
    }

    @Override
    public List<Board> searchBoard(Board board) {
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
    public void deleteBoard(Long boardId) throws Exception{
        Board selectBoard = boardMapper.getBoardById(boardId);
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");

        if(selectBoard == null){
            throw new NotFoundException("존재하지 않는 게시물입니다");
        }

        if(!jwtUtil.getUserIdFromJWT(token).equals(selectBoard.getBoard_Id())){
            throw new UnauthorizedException("권한이 없습니다");
        }
        boardMapper.deleteBoard(boardId);
    }
}
