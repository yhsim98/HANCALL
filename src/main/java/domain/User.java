package domain;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

public class User {
    private Long id;

    @NotBlank(message="이메일을 입력해주세요")
    @Email(message="이메일 형식에 맞춰주세요")
    private String email;
    @NotBlank(message="비밀번호를 입력해주세요")
    private String password;
    @NotBlank(message="이름을 입력해주세요")
    @Length(min=2, max=8, message = "이름을 2~8자 사이로 입력해주세요")
    private String name;
    @NotBlank(message="사용하실 별명을 입력해주세요")
    private String nickname;
    @Pattern(regexp="^\\d{2,3}\\d{3,4}\\d{4}$", message = "'-'은 제외하고 형식에 맞게 입력해주세요")
    private String phone;
    private String join_date;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJoin_date() {
        return join_date;
    }

    public void setJoin_date(String join_date) {
        this.join_date = join_date;
    }
}
