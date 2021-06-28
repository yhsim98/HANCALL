package domain;

public class Board {
    private Long board_Id;
    private String writer_Id;
    private String starting_Point;
    private String destination;
    private String created_Date;
    private String update_Date;
    private String title;
    private String content;
    private String nickname;

    public String getUpdate_Date() {
        return update_Date;
    }

    public void setUpdate_Date(String update_Date) {
        this.update_Date = update_Date;
    }

    public Long getBoard_Id() {
        return board_Id;
    }

    public void setBoard_Id(Long board_Id) {
        this.board_Id = board_Id;
    }

    public String getWriter_Id() {
        return writer_Id;
    }

    public void setWriter_Id(String writer_Id) {
        this.writer_Id = writer_Id;
    }

    public String getStarting_Point() {
        return starting_Point;
    }

    public void setStarting_Point(String starting_Point) {
        this.starting_Point = starting_Point;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCreated_Date() {
        return created_Date;
    }

    public void setCreated_Date(String created_Date) {
        this.created_Date = created_Date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
