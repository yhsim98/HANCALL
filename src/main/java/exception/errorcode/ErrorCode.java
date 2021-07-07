package exception.errorcode;

public enum ErrorCode {

    //common
    ACCESS_DENIED(402, "C001", "Access denied"),

    // User
    USER_NOT_FOUND(404, "U001", "user not exist"),
    EMAIL_DUPLICATION(400, "U002", "email is duplication"),
    NICKNAME_DUPLICATION(400, "U003", "nickname is duplication"),
    INVALID_EMAIL(401, "U004", "invalid email"),
    INVALID_PASSWORD(401, "U005", "password not match"),

    // Board
    BOARD_NOT_FOUND(404, "B001", "board not exist"),

    // COMMENT
    COMMENT_NOT_FOUND(404, "CTO01", "comment not exist"),

    // Participant
    ALREADY_PARTICIPATE(400, "P001", "This user is already participate")
    ;
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
