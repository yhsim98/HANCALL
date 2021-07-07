package service;

import domain.Participant;

import java.util.List;

public interface ParticipantService {
    List<Participant> getParticipantsList(Long boardId);
    void participate(Long boardId) throws Exception;
    void cancel(Long boardId);
    void deleteAllParticipantInBoard(Long boardId);
}
