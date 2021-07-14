package exception;

import exception.errorcode.ErrorCode;

public class BusinessException extends CustomException{
    public BusinessException(ErrorCode errorCode) {
        super(errorCode);
    }
}
