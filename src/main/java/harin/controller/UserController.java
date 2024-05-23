package harin.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import harin.vo.UserLoginVo;
import lombok.extern.slf4j.Slf4j;
import harin.service.UserService;
import harin.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/userList")
    public String getUserList(Model model) {
        List<UserVo> userList = userService.getUserList();
        model.addAttribute("list", userList);
        return "userListPage";
    }

    @GetMapping("/login")
    public String loginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken)
            return "loginPage";
        log.info("login 호출");
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signupPage() {  // 회원 가입 페이지
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken)
            return "signupPage";
        log.info("signup 호출");
        return "redirect:/";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken)
            return "homePage";
        log.info("home 호출");
        // 사용자 정보 가져오기
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = principal.getUsername(); // 현재 로그인한 사용자의 이메일 가져오기
        UserVo user = userService.getUserByEmail(email);

        // 모델에 사용자 정보 추가
        model.addAttribute("user", user);
        return "homePage";
    }

    @PostMapping("/signup")
    public String signup(UserVo userVo) { // 회원 가입
        userService.signup(userVo);
        System.out.println("유저 데이터 받았니?: " + userVo);
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(UserLoginVo userLoginVo, HttpSession session, RedirectAttributes redirectAttributes, Model model) {
        log.info("로그인 요청: email={}, password={}", userLoginVo.getEmail(), userLoginVo.getPassword());

        // 사용자 정보 가져오기
        UserVo userVo = userService.getUserByEmail(userLoginVo.getEmail());

        if (userLoginVo.getPassword().equals(userVo.getPassword())) {
            log.info("로그인 성공: {}", userLoginVo.getEmail());
            session.setAttribute("email", userVo.getEmail());
            log.info("세션스토리지 이메일 {}", userVo.getEmail());

            // 모델에 사용자 정보 추가
            model.addAttribute("user", userVo);

            return "redirect:/home";
        }
        return "redirect:/login?error=-1";
    }

    @PostMapping("/user")
    public String getUserDetails(String email, Model model) {
        UserVo user = userService.getUserByEmail(email);
        log.info("view my info api 호출 {}", user);
        if (user != null) {
            model.addAttribute("user", user);
        }
        return "homePage"; // 화면에 사용자 정보를 표시할 페이지로 리턴
    }
}
