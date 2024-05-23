package harin.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import harin.mapper.UserMapper;
import harin.vo.UserVo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<UserVo> getUserList() {
        return userMapper.getUserList();
    }

    @Transactional
    public UserVo getUserByEmail(String email1) {
        String email = "'"+email1+"'";
        return userMapper.getUserByEmail(email);
    }

    @Transactional
    public void signup(UserVo userVo) { // 회원 가입
        log.info("회원가입 api 호출2 = {}", userVo);
        String username = "'"+userVo.getUsername()+"'";
        String email = "'"+userVo.getEmail()+"'";
        String password = "'"+userVo.getPassword()+"'";
        userMapper.insertUser(username, email, password);
    }
}
