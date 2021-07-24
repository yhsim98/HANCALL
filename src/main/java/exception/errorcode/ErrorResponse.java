package exception.errorcode;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorResponse {
    private String message;
    private String code;
    private int status;
    private List<FieldError> errors;

    public ErrorResponse(ErrorCode errorCode){
        this.message = errorCode.getMessage();
        this.code = errorCode.getCode();
        this.status = errorCode.getStatus();
    }

    public ErrorResponse(ErrorCode errorCode, BindingResult bindingResult){
        this(errorCode);
        this.errors = bindingResult.getFieldErrors();
    }

    public static ErrorResponse of(ErrorCode errorCode){
        return new ErrorResponse(errorCode);
    }
    public static ErrorResponse of(ErrorCode errorCode, BindingResult bindingResult){
        return new ErrorResponse(errorCode, bindingResult);
    }


    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }

    public List<FieldError> getErrors() {
        return errors;
    }
}
