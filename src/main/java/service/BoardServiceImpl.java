package service;


import domain.Board;
import domain.Participant;
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
    private final CommentService commentService;
    private final ParticipantService participantService;
    private final JwtUtil jwtUtil;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper, CommentService commentService, ParticipantService participantService, JwtUtil jwtUtil) {
        this.boardMapper = boardMapper;
        this.commentService = commentService;
        this.participantService = participantService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Board createBoard(Board board) {
        String token = getTokenFromServlet();

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
        Board selectBoard = boardMapper.getBoardById(boardId);

        if("".equals(selectBoard) || selectBoard == null){
            throw new NotFoundException("존재하지 않는 게시물입니다");
        }

        return boardMapper.getBoardById(boardId);
    }


    @Override
    public void updateBoard(Board board, Long boardId) throws Exception{
        Board selectBoard = boardMapper.getBoardById(boardId);
        String token = getTokenFromServlet();

        if("".equals(selectBoard) || selectBoard == null){
            throw new NotFoundException("존재하지 않는 게시물입니다");
        }

        if(!jwtUtil.getUserIdFromJWT(token).equals(selectBoard.getWriter_Id())){
            throw new UnauthorizedException("권한이 없습니다");
        }

        board.setBoard_Id(boardId);
        boardMapper.updateBoard(board);
    }

    @Override
    public List<Board> searchBoard(String startingPoint, String destination) {
        Board board = new Board();

        board.setStarting_Point(startingPoint);
        board.setDestination(destination);

        return boardMapper.searchBoard(board);
    }

    @Override
    public void deleteBoard(Long boardId) throws Exception{
        Board selectBoard = boardMapper.getBoardById(boardId);
        String token = getTokenFromServlet();

        if("".equals(selectBoard) || selectBoard == null){
            throw new NotFoundException("존재하지 않는 게시물입니다");
        }

        if(!jwtUtil.getUserIdFromJWT(token).equals(selectBoard.getWriter_Id())){
            throw new UnauthorizedException("권한이 없습니다");
        }

        participantService.deleteAllParticipantInBoard(boardId);
        commentService.deleteBoardComments(boardId);
        boardMapper.deleteBoard(boardId);
    }

    private String getTokenFromServlet(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader("Authorization");
    }
}
