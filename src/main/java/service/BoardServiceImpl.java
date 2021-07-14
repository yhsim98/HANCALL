package service;


import domain.Board;
import exception.ConflictException;
import exception.ForbiddenException;
import exception.EntityNotFoundException;
import exception.errorcode.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import repository.BoardMapper;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class BoardServiceImpl implements BoardService{

    private final BoardMapper boardMapper;
    private final CommentService commentService;
    private final ParticipantService participantService;
    private final JwtUtil jwtUtil;

    private static Long boardNum = 0L;
    private final static int pageSize = 10;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper, CommentService commentService, ParticipantService participantService, JwtUtil jwtUtil) {
        this.boardMapper = boardMapper;
        this.commentService = commentService;
        this.participantService = participantService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Board createBoard(Board board) throws Exception {
        String token = getTokenFromServlet();
        board.setWriter_Id(jwtUtil.getUserIdFromJWT(token));
        boardMapper.insertBoard(board);

        participantService.participate(board.getBoard_Id());
        return boardMapper.getBoardById(board.getBoard_Id());
    }

    @Override
    public Map getBoardList(int page) {


        Map result = new HashMap();
        result.put("data", boardMapper.getBoardList());
        return result;
    }


    @Override
    public Map getBoard(Long boardId) throws Exception{
        Board selectBoard = boardMapper.getBoardById(boardId);

        if(selectBoard == null){
            throw new EntityNotFoundException(ErrorCode.Board_NOT_FOUND);
        }

        selectBoard = boardMapper.getBoardById(boardId);
        selectBoard.setNow_Participants(participantService.countParticipants(boardId));

        Map result = new HashMap();
        result.put("data", selectBoard);
        return result;
    }


    @Override
    public void updateBoard(Board board) throws Exception{
        Board selectBoard = boardMapper.getBoardById(board.getBoard_Id());
        String token = getTokenFromServlet();

        if(selectBoard == null){
            throw new EntityNotFoundException(ErrorCode.Board_NOT_FOUND);
        }

        if(!jwtUtil.getUserIdFromJWT(token).equals(selectBoard.getWriter_Id())){
            throw new ForbiddenException(ErrorCode.ACCESS_DENIED);
        }

        if(participantService.countParticipants(board.getBoard_Id()) > board.getMax_Participants()){
            throw new ConflictException(ErrorCode.INVALID_MAX_PARTICIPANTS);
        }

        boardMapper.updateBoard(board);
    }

    @Override
    public Map searchBoard(String startingPoint, String destination) throws Exception {
        Board board = new Board();

        if(startingPoint == null && destination == null)
            throw new ConflictException(ErrorCode.INVALID_INPUT_VALUE);

        board.setStarting_Point(startingPoint);
        board.setDestination(destination);

        Map result = new HashMap();
        result.put("data", boardMapper.searchBoard(board));
        return result;
    }

    @Override
    public void deleteBoard(Long boardId) throws Exception{
        Board selectBoard = boardMapper.getBoardById(boardId);
        String token = getTokenFromServlet();

        if(selectBoard == null){
            throw new EntityNotFoundException(ErrorCode.Board_NOT_FOUND);
        }

        if(!jwtUtil.getUserIdFromJWT(token).equals(selectBoard.getWriter_Id())){
            throw new ForbiddenException(ErrorCode.ACCESS_DENIED);
        }

        participantService.deleteAllParticipantInBoard(boardId);
        commentService.deleteAllCommentsInBoard(boardId);
        boardMapper.deleteBoard(boardId);
    }

    @Override
    public int getMaxParticipants(Long boardId) {
        return boardMapper.getMaxParticipants(boardId);
    }


    private String getTokenFromServlet(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader("Authorization");
    }
}
