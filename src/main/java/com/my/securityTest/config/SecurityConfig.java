package com.my.securityTest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/login", "/loginProc").permitAll()
                        .requestMatchers("/join", "/joinProc").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/user/**")
                                    .hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                );

        http
                .formLogin((auth)-> auth
                        .loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .usernameParameter("username")
                                .defaultSuccessUrl("/")
//                        .usernameParameter("email")
                        .permitAll()
                );
        http
                .logout((auth) -> auth
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .permitAll());

        http
                .sessionManagement((auth) -> auth
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        // 로그인과 같은 처리로 세션이 활성화되지 않으면 타임아웃이 발생 안함.
                        .invalidSessionUrl("/login")
                        // 유효하지 않은 세션 -> 로그인 화면으로
                        .sessionFixation().none()
                        // 세션 고정되지 않도록 설정
                        .maximumSessions(1).expiredUrl("/login"));

//        http
//                .csrf((auth) -> auth.disable());
        return http.build();
    }
}
