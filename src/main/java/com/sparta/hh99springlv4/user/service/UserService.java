package com.sparta.hh99springlv4.user.service;


import com.sparta.hh99springlv4.user.dto.SignupRequestDto;
import com.sparta.hh99springlv4.user.dto.SignupResponseDto;
//import com.sparta.hh99springlv4.user.entity.DepartmentEnum;
import com.sparta.hh99springlv4.user.entity.User;
import com.sparta.hh99springlv4.user.entity.UserRoleEnum;
import com.sparta.hh99springlv4.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    // 회원 가입
    public SignupResponseDto signup(SignupRequestDto signupRequestDto) {
        String userEmail = signupRequestDto.getUserEmail();  // requestDto 에서 getUsername 가져와 변수 username 에 담음.
        String userPassword = passwordEncoder.encode(signupRequestDto.getUserPassword());  // 평문을 암호화 해서 password 에 담음.


        // email 중복확인
        Optional<User> checkEmail = userRepository.findByUserEmail(userEmail);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 사용자 ROLE 확인 (권한확인)
        UserRoleEnum role = UserRoleEnum.USER;

        // signupRequestDto.equals(auth) 대체 검토 (매니저 외 권한 확장 가능성 고려)
        if (signupRequestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(signupRequestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = new User(signupRequestDto, userPassword, role);
        return new SignupResponseDto(userRepository.save(user));

    }


}
