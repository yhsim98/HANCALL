package repository;

import domain.Participant;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ParticipantMapper {
    List<Participant> getParticipantsList(Long boardId);
    void insertParticipant(Participant participant);
    void deleteParticipant(Participant participant);
    Participant getParticipant(Participant participant);
    void deleteAllParticipantInBoard(Long boardId);
}
