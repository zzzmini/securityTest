package com.my.securityTest.repository;

import com.my.securityTest.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository
        extends JpaRepository<UserEntity, Long> {

    Boolean existsByUsername(String username);

    UserEntity findByUsername(String username);

    Boolean existsByEmail(String email);

    UserEntity findByEmail(String email);

    Optional<UserEntity> findByProviderAndProviderId(
            String provider, String providerId);
}
