package service;

import domain.Participant;
import exception.ConflictException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import repository.BoardMapper;
import repository.ParticipantMapper;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public List<Participant> getParticipantsList(Long boardId) {
        return participantMapper.getParticipantsList(boardId);
    }

    @Override
    public void participate(Long boardId) throws Exception{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");

        Participant newParticipant = new Participant();

        newParticipant.setUser_Id(jwtUtil.getUserIdFromJWT(token));
        newParticipant.setBoard_Id(boardId);

        if(participantMapper.getParticipant(newParticipant) != null){
            throw new ConflictException("이미 참가하였습니다");
        }

        if(countParticipants(boardId) >= boardMapper.getMaxParticipants(boardId)){
            throw new ConflictException("참여가능한 최대 인원입니다");
        }

        participantMapper.insertParticipant(newParticipant);
    }

    @Override
    public void cancel(Long boardId) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Authorization");

        Participant participant = new Participant();

        participant.setUser_Id(jwtUtil.getUserIdFromJWT(token));
        participant.setBoard_Id(boardId);

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
