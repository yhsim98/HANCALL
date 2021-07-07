package exception.errorcode;

public enum ErrorCode {

    EMAIL_ALREADY_EXIST(409, "C001", "email already used");
    USER_NOT_FOUND()

    private final int status;
    private final String code;
    private final String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
