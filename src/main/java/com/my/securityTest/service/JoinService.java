package com.my.securityTest.service;

import com.my.securityTest.dto.JoinDTO;
import com.my.securityTest.entity.UserEntity;
import com.my.securityTest.entity.UserRole;
import com.my.securityTest.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserEntityRepository userRepository;

    public void joinProcess(JoinDTO joinDTO) {
        // 기존 같은 유저가 있는지 확인
        Boolean isUser = userRepository
                .existsByUsername(joinDTO.getUsername());

        if (isUser) {
            return;
        }

        // 없으면 회원가입
        UserEntity data = new UserEntity();
        data.setUsername(joinDTO.getUsername());
        data.setPassword(
                bCryptPasswordEncoder
                        .encode(joinDTO.getPassword())
        );
        if (data.getUsername().equals("admin")) {
            data.setRole(UserRole.ROLE_ADMIN);
        } else {
            data.setRole(UserRole.ROLE_USER);
        }

        userRepository.save(data);
    }

    public void loginProcess(JoinDTO joinDTO) {

    }
}
