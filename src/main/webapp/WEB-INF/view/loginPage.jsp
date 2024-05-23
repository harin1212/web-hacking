<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
    <script type="text/javascript">
        // 이 함수는 로그인 버튼 클릭 시 호출됩니다.
        function loginUser() {
            var emailInput = document.getElementById("email"); // 이메일 입력란
            var email = emailInput.value; // 입력된 이메일 값

            // 이메일이 입력되었는지 확인
            if (email) {
                localStorage.setItem('email', email); // 입력된 이메일을 로컬 스토리지에 저장
                console.log('Email stored in local storage:', email);
            }
        }
    </script>
</head>
<body>
<form action="/login" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

    <h2>로그인</h2>
    <div>
        <input type="text" id="email" name="email" placeholder="Email"/>
    </div>
    <div>
        <input type="password" name="password" placeholder="Password"/>
    </div>

    <button type="submit" onclick="loginUser()">로그인</button>
    <button type="button" onclick="location.href='signup'">회원가입</button>
</form>
</body>
</html>
