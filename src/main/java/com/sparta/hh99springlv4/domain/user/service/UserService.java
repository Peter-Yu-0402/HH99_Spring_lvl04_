package com.sparta.hh99springlv4.domain.user.service;


import com.sparta.hh99springlv4.domain.user.dto.SignupRequestDto;
import com.sparta.hh99springlv4.domain.user.dto.SignupResponseDto;
import com.sparta.hh99springlv4.domain.user.entity.User;
import com.sparta.hh99springlv4.domain.user.entity.UserRoleEnum;
import com.sparta.hh99springlv4.domain.user.repository.UserRepository;
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
        // requestDto에서 getUsername 가져와 변수 username에 담음.
        String userEmail = signupRequestDto.getUserEmail();
        // 평문을 암호화 해서 password에 담음
        String userPassword = passwordEncoder.encode(signupRequestDto.getUserPassword());

        // email 중복확인
        Optional<User> checkEmail = userRepository.findByUserEmail(userEmail);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 초기값이 'USER'인 User 클래스 role
        UserRoleEnum role;

        // 관리자 토큰 일치 여부 확인
        if (!ADMIN_TOKEN.equals(signupRequestDto.getAdminToken())) {
            throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
        } else {
            // User 클래스 role을 'ADMIN'으로 변경
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = new User(signupRequestDto, userPassword, role);
        return new SignupResponseDto(userRepository.save(user));
    }
}
