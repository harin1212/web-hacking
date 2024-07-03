# SQL Injection 실험 환경 세팅 및 보안 기법 적용

## 실험 환경 세팅

### 1. Spring 프로젝트 생성 및 설정

- **데이터베이스**: MySQL을 사용합니다. `application.yml` 파일에서 데이터베이스의 URL과 비밀번호를 입력하여 MySQL 데이터베이스와 연결을 설정합니다.
- **화면**: 입력받는 화면 구현을 위해 타임리프를 이용해 JSP로 서버사이드 렌더링을 이용하여 화면을 구현합니다. 로그인, 회원가입, 내 정보 페이지를 구현합니다.
- **보안 설정**: 로그인한 사용자를 관리하기 위해 `SecurityConfig`를 사용합니다. 로그인한 사용자만 내 정보를 볼 수 있도록 설정해줍니다.

### 2. 취약한 서버 구축

- MVC 패턴으로 `User`의 엔티티, 서비스, 컨트롤러를 구현합니다.
- `MyBatisConfig`를 설정하여 `sqlSession`을 Bean으로 주입해줍니다.
- 타임리프를 이용하여 JSP로 화면을 구축합니다. 회원가입, 로그인, 내 정보 페이지를 구현합니다.
- `User` 컨트롤러에서 `/signup`, `/login`, `/user` URL로 데이터를 처리하는 API를 구현합니다.
- MyBatis로 데이터베이스 연결을 위해 `UserMapper`를 구현합니다. 이 때, `${}` 문법을 사용하여 입력받은 파라미터를 SQL 쿼리에 직접 삽입합니다.

## SQL Injection 공격

1. 악의적인 공격자가 회원가입을 진행합니다.
2. 1번에서 입력한 정보로 로그인을 진행합니다.
3. 로그인에 성공한 공격자는 내 정보를 확인할 수 있는 페이지에 접근합니다.
4. 이메일을 입력하는 인풋 텍스트에서 `' OR '1'='1` 을 입력하여 SQL 인젝션 공격을 합니다.
5. MySQL에서 데이터베이스를 조회했을 때와 동일하게 모든 사용자의 정보가 출력되는 것을 확인하여 SQL 인젝션 공격에 성공했음을 확인할 수 있습니다.

## 웹 보안 기법 적용

### 1. MyBatis에서의 SQL Injection 방어 기법

#### A. Parameter Binding 사용하기

- `UserMapper`를 구현할 때, `#{}` 문법을 사용하여 파라미터가 바인딩되어 SQL 쿼리문에 입력값이 직접 삽입되는 것을 방지합니다.

#### B. 입력값 검증 (Validation)

- 입력값의 형식을 검증하는 함수를 만들어 `'`, `=` 와 같은 문자열이 삽입되지 않도록 구현합니다.

### 2. 웹 보안 기법 적용 결과

- 이전과 달리 인풋 텍스트에 `' OR '1'='1` 을 입력해도 User Details를 볼 수 없음을 확인할 수 있습니다.

## 결과 분석

SQL 인젝션은 웹 보안에서 매우 중요한 취약점으로, OWASP Top 10에서도 상위에 위치하는 심각한 위협입니다. 악의적인 공격자가 웹 애플리케이션에 악성 SQL 코드를 삽입하여 데이터베이스를 조작하거나 민감한 정보를 유출할 수 있기 때문입니다.

본 보고서는 MySQL을 데이터베이스로 사용하고, Spring 프레임워크를 통해 서버를 구축하며, MyBatis를 이용해 데이터베이스와 상호작용하는 웹 애플리케이션에서의 SQL 인젝션 취약점을 분석하고 공격을 수행하며 웹 보안 기법의 효과를 입증하였습니다.

이 보고서에서는 MyBatis에서 데이터베이스 연결 시 사용되는 `${}` 와 `#{}` 문법의 차이를 분석하고, SQL 인젝션에 안전한 `#{}` 문법을 사용한 `PreparedStatement`의 효과를 실험을 통해 입증했습니다. `PreparedStatement`는 파라미터 바인딩을 통해 사용자 입력을 SQL 쿼리에 직접 삽입하지 않고 안전하게 처리하여, SQL 인젝션 공격을 방지하는 데 효과적임을 확인했습니다. 또한, 입력값 검증 기법을 적용하여 악의적인 쿼리를 사전에 걸러내는 방법도 효과적임을 입증했습니다.

이러한 보안 기법들을 통해 SQL 인젝션 공격을 효과적으로 방어할 수 있었습니다. 결과적으로, Spring 프레임워크로 웹 개발을 진행할 때 발생할 수 있는 보안 취약점인 SQL 인젝션에 대해 적절한 방어책을 마련함으로써, 안전한 웹 서버를 구축할 수 있었습니다.