package controller;

import exception.ConflictException;
import exception.ForbiddenException;
import exception.NotFoundException;
import exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({ConflictException.class})
    public ResponseEntity handleException(final ConflictException e){
        return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity conflictException(final NotFoundException e){
        return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity unauthorizedException(final UnauthorizedException e){
        return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ForbiddenException.class})
    public ResponseEntity unauthorizedException(final ForbiddenException e){
        return new ResponseEntity(e.getMessage(), HttpStatus.FORBIDDEN);
    }

}
