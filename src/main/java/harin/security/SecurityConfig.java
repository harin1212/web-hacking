package harin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/login", "/signup", "/home", "/user", "/home").permitAll()
                .anyRequest().permitAll();
        http
                .csrf().disable();

        http
            .formLogin()
                .loginPage("/login")    // GET 요청
                .loginProcessingUrl("/auth")    // POST 요청
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/");

        http
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");

        return http.build();
    }
 }
