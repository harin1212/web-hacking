package harin.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import harin.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM user")
    List<UserVo> getUserList(); // User 테이블 가져오기

    @Insert("INSERT INTO user(username, email, password) VALUES(${username}, ${email}, ${password})")
    void insertUser(@Param("username") String username, @Param("email") String email, @Param("password") String password); // 회원 가입

    @Select("SELECT * FROM user WHERE email = ${email}")
    UserVo getUserByEmail(@Param("email") String email); // 회원 정보 가져오기

    @Select("SELECT * FROM user WHERE id = ${id}")
    UserVo getUserById(@Param("id") Long id); // 회원 정보 가져오기 by ID
}
