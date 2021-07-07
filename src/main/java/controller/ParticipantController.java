package controller;

import annotation.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ParticipantService;

@Controller
@RequestMapping(value="/participant/*")
public class ParticipantController {

    private final ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService) {
        this.participantService = participantService;
    }

    //참가자 조회
    @ResponseBody
    @RequestMapping(value="/{boardId}", method = RequestMethod.GET)
    public ResponseEntity getParticipantsList(@PathVariable("boardId") Long boardId){
        return new ResponseEntity(participantService.getParticipantsList(boardId), HttpStatus.OK);
    }

    //참가자 추가
    @Auth
    @RequestMapping(value="/{boardId}", method = RequestMethod.POST)
    public ResponseEntity participate(@PathVariable("boardId") Long boardId) throws Exception {
        participantService.participate(boardId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    //참가 취소
    @Auth
    @RequestMapping(value="/{boardId}", method = RequestMethod.DELETE)
    public ResponseEntity cancel(@PathVariable("boardId") Long boardId){
        participantService.cancel(boardId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
