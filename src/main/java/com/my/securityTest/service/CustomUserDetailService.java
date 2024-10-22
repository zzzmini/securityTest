package com.my.securityTest.service;

import com.my.securityTest.dto.CustomUserDetails;
import com.my.securityTest.entity.UserEntity;
import com.my.securityTest.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class CustomUserDetailService
        implements UserDetailsService {
    @Autowired
    UserEntityRepository entityRepository;

    @Override
    public UserDetails loadUserByUsername
            (String username) throws UsernameNotFoundException {
        UserEntity userData = entityRepository
                .findByUsername(username);
        // 생성한 엔티티를 UserDetails 로 전송
        if (!ObjectUtils.isEmpty(userData)) {
            return new CustomUserDetails(userData);
        }
        return null;
    }
}
