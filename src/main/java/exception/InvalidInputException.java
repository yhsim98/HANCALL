package exception;

import exception.errorcode.ErrorCode;

public class InvalidInputException extends BusinessException{
    public InvalidInputException(ErrorCode errorCode) {
        super(errorCode);
    }
}
