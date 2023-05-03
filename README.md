# Personal Project
개인 프로젝트

클라이언트로부터 3개의 숫자를 입력받고 나머지 3개의 숫자는 랜덤한 숫자를 자동으로 생성 후 사용자에게 보여주는 프로젝트
---------------
# 사용 기술
1. HTML5
2. CSS3
3. Java ( JDK 17 )
4. Spring boot ( 3.0.5 )
5. Gradle
---------------
# 약속
1. MVC패턴을 이용하여 로또 게임의 전반적인 구조를 형성한다.
2. 백엔드 프로젝트이므로 프론트 작업 최대 기한은 하루로 기간을 정한다.
3. 프론트 엔드에서 Thymeleaf를 최대한 이용한다.
---------------

# 진행상황
1. @Repository , @Service , @Controller 구현 (완료)
2. 로또 번호를 save할 시에 F5를 누르면 자동으로 무한 저장되는 현상 해결(Post-Redirect-Get 적용완료 )
3. 로또 번호의 범위는 1 ~ 45 이므로 클라이언트가 그 외의 숫자를 입력 받을 시에 저장이 안되도록 구현 (Validation 사용완료)
4. 회원가입이라는 버튼을 생성 후 쿠키 및 세션을 이용하여 로그인 관리 (진행 중)