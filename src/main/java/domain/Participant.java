package domain;

public class Participant {
    private Long board_Id;
    private Long user_Id;
    private String nickname;
    private int max_participants;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getBoard_Id() {
        return board_Id;
    }

    public void setBoard_Id(Long board_Id) {
        this.board_Id = board_Id;
    }

    public Long getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Long user_Id) {
        this.user_Id = user_Id;
    }

    public int getMax_participants() {
        return max_participants;
    }

    public void setMax_participants(int max_participants) {
        this.max_participants = max_participants;
    }

}
