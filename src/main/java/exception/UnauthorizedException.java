package exception;

import exception.errorcode.ErrorCode;

public class UnauthorizedException extends CustomException{

    public UnauthorizedException(ErrorCode errorCode){
        super(errorCode);
    }

    public UnauthorizedException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
}
