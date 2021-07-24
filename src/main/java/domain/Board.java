package domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Board {
    private Long board_Id;
    private Long writer_Id;
    @NotBlank(message="출발지점을 입력해주세요")
    private String starting_Point;
    @NotBlank(message="도착지점을 입력해주세요")
    private String destination;
    private String created_Date;
    private String update_Date;
    @NotBlank(message="제목을 입력해주세요")
    private String title;
    @NotBlank(message="내용을 입력해주세요")
    private String content;
    private String nickname;
    @NotNull
    private int max_Participants;
    private int now_Participants;

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

    public Long getWriter_Id() {
        return writer_Id;
    }

    public void setWriter_Id(Long writer_Id) {
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

    public int getMax_Participants() {
        return max_Participants;
    }

    public void setMax_Participants(int max_Participants) {
        this.max_Participants = max_Participants;
    }

    public int getNow_Participants() {
        return now_Participants;
    }

    public void setNow_Participants(int now_Participants) {
        this.now_Participants = now_Participants;
    }

}
