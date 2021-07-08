package service;


import domain.Board;
import exception.ConflictException;
import exception.ForbiddenException;
import exception.NotFoundException;
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
    public Board createBoard(Board board) throws Exception {
        String token = getTokenFromServlet();

        board.setWriter_Id(jwtUtil.getUserIdFromJWT(token));
        boardMapper.insertBoard(board);
        participantService.participate(board.getBoard_Id());

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

        selectBoard = boardMapper.getBoardById(boardId);
        selectBoard.setMax_Participants(participantService.countParticipants(boardId));
        return selectBoard;
    }


    @Override
    public void updateBoard(Board board, Long boardId) throws Exception{
        Board selectBoard = boardMapper.getBoardById(boardId);
        String token = getTokenFromServlet();

        if("".equals(selectBoard) || selectBoard == null){
            throw new NotFoundException("존재하지 않는 게시물입니다");
        }

        if(!jwtUtil.getUserIdFromJWT(token).equals(selectBoard.getWriter_Id())){
            throw new ForbiddenException("권한이 없습니다");
        }

        if(participantService.countParticipants(boardId) > board.getMax_Participants()){
            throw new ConflictException("현재 참여인원보다 적습니다");
        }

        board.setBoard_Id(boardId);
        boardMapper.updateBoard(board);
    }

    @Override
    public List<Board> searchBoard(String startingPoint, String destination) throws Exception {
        Board board = new Board();

        if("".equals(startingPoint) && "".equals(destination))
            throw new ConflictException("둘 중 하나는 입력해야 합니다");

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
            throw new ForbiddenException("권한이 없습니다");
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
