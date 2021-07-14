package exception.errorcode;

public enum ErrorCode {

    // common
    INVALID_JWT(401, "C001", "jwt is invalid"),
    ACCESS_DENIED(403, "C002", "no authorized"),
    INVALID_INPUT_VALUE(400, "C003", " Invalid Input Value"),

    // User
    USER_NOT_FOUND(400, "C002", "user not found"),
    EMAIL_DUPLICATION(409, "U001", "email is duplication"),
    NICKNAME_DUPLICATION(409, "U002", "nickname is duplication"),
    INVALID_EMAIL(403, "U003", "invalid email"),
    INVALID_PASSWORD(403, "U004", "password not match"),

    // Board
    Board_NOT_FOUND(400, "C002", "board not found"),
    INVALID_MAX_PARTICIPANTS(400, "B002", "max participants value is too small"),

    // COMMENT
    COMMENT_NOT_FOUND(400, "C002", "comment not found"),

    // Participant
    ALREADY_PARTICIPATE(409, "P001", "already participate"),
    ALREADY_FULL(400, "P002", "already full"),
    PARTICIPANT_NOT_FOUND(400, "P002", "participant not found")
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
