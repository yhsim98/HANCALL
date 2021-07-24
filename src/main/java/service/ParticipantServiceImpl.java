package service;

import domain.Participant;
import exception.ConflictException;
import exception.EntityNotFoundException;
import exception.errorcode.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import repository.BoardMapper;
import repository.ParticipantMapper;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class ParticipantServiceImpl implements ParticipantService{

    private final ParticipantMapper participantMapper;
    private final BoardMapper boardMapper;
    private final JwtUtil jwtUtil;

    @Autowired
    public ParticipantServiceImpl(ParticipantMapper participantMapper, BoardMapper boardMapper, JwtUtil jwtUtil) {
        this.participantMapper = participantMapper;
        this.boardMapper = boardMapper;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Map getParticipantsList(Long boardId) {
        Map result = new HashMap();
        result.put("data", participantMapper.getParticipantsList(boardId));
        return result;
    }

    @Transactional
    @Override
    public void participate(Long boardId) throws Exception{
        String token = jwtUtil.getTokenFromServlet();

        Participant newParticipant = new Participant();

        newParticipant.setUser_Id(jwtUtil.getUserIdFromJWT(token));
        newParticipant.setBoard_Id(boardId);

        if(participantMapper.getParticipant(newParticipant) != null && participantMapper.isDeleted(newParticipant) == 0){
            throw new ConflictException(ErrorCode.ALREADY_PARTICIPATE);
        }

        if(countParticipants(boardId) >= boardMapper.getMaxParticipants(boardId)){
            throw new ConflictException(ErrorCode.ALREADY_FULL);
        }

        if(participantMapper.getParticipant(newParticipant) != null && participantMapper.isDeleted(newParticipant) == 1) {
            participantMapper.restore(newParticipant);
            return;
        }

        participantMapper.insertParticipant(newParticipant);
    }

    @Transactional
    @Override
    public void cancel(Long boardId) {
        String token = jwtUtil.getTokenFromServlet();

        Participant participant = new Participant();

        participant.setUser_Id(jwtUtil.getUserIdFromJWT(token));
        participant.setBoard_Id(boardId);

        if(participantMapper.getParticipant(participant) == null){
            throw new EntityNotFoundException(ErrorCode.PARTICIPANT_NOT_FOUND);
        }

        participantMapper.deleteParticipant(participant);
    }

    @Override
    public void deleteAllParticipantInBoard(Long boardId) {
        participantMapper.deleteAllParticipantInBoard(boardId);
    }

    @Override
    public int countParticipants(Long boardId) {
        return participantMapper.countParticipants(boardId);
    }
}
