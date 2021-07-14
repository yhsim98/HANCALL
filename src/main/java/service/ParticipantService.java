package service;

import domain.Participant;

import java.util.List;
import java.util.Map;

public interface ParticipantService {
    Map getParticipantsList(Long boardId);
    void participate(Long boardId) throws Exception;
    void cancel(Long boardId);
    void deleteAllParticipantInBoard(Long boardId);
    int countParticipants(Long boardId);
}
