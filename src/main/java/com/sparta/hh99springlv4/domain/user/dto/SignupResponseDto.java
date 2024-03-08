package com.sparta.hh99springlv4.domain.user.dto;


import com.sparta.hh99springlv4.domain.user.entity.User;
import com.sparta.hh99springlv4.domain.user.entity.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupResponseDto {

    private String userEmail; //이메일
    private String userPassword; // 비밀번호
    private String userGender; // 성별
    private String userPhone; //전화번호
    private String userAddress; // 주소
    private UserRoleEnum role;  //권한

    public SignupResponseDto(User user) {
        this.userEmail = user.getUserEmail();
        this.userPassword = user.getUserPassword();
        this.userGender = user.getUserGender();
        this.userPhone = user.getUserPhone();
        this.userAddress = user.getUserAddress();
        this.role = user.getRole();
    }
}
