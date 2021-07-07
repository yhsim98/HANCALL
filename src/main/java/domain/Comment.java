package domain;

public class Comment {
    private Long comment_Id;
    private Long board_Id;
    private Long writer_Id;
    private String content;
    private String update_Date;
    private String nickname;

    public Long getComment_Id() {
        return comment_Id;
    }

    public void setComment_Id(Long comment_Id) {
        this.comment_Id = comment_Id;
    }

    public Long getBoard_Id() {
        return board_Id;
    }

    public void setBoard_Id(Long board_Id) {
        this.board_Id = board_Id;
    }

    public Long getWriter_Id() {
        return writer_Id;
    }

    public void setWriter_Id(Long writer_Id) {
        this.writer_Id = writer_Id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUpdate_Date() {
        return update_Date;
    }

    public void setUpdate_Date(String update_Date) {
        this.update_Date = update_Date;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String writerNickname) {
        this.nickname = writerNickname;
    }
}
