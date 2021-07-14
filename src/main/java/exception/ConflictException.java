package exception;

import exception.errorcode.ErrorCode;

public class ConflictException extends BusinessException{

    public ConflictException(ErrorCode errorCode){
        super(errorCode);
    }

}
