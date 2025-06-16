
# 사용자 관리 시스템


Spring Framework 기반의 사용자 관리 웹 애플리케이션입니다.

<div style="display: flex; justify-content: space-around; align-items: flex-start;">
  <img src="/screenshots/screenshot11.png" style="width: 48%;"/>
  <img src="/screenshots/screenshot22.png" style="width: 48%;"/>
</div>

## 주요 기능

### 1. 사용자 관리
- 회원가입
  - 사용자 정보 입력 (이름, ID, 비밀번호, 전화번호, 주소)
  - 비밀번호 SHA-512 암호화 저장
  - 기본 사용자 타입은 'guest'로 설정

- 로그인/로그아웃
  - ID/PW 기반 로그인
  - 세션 기반 로그인 상태 관리
  - 로그아웃 기능

- 마이페이지
  - 개인정보 조회
  - 개인정보 수정 (이름, 전화번호, 주소, 사용자 타입)

### 2. 관리자 기능
- 전체 사용자 목록 조회
- 전체 사용자 수 통계
- 오늘 가입한 사용자 수 통계

### 3. 보안 기능
- 비밀번호 SHA-512 해시 암호화
- 세션 기반 인증
- SQL Injection 방지 (PreparedStatement 사용)

## 기술 스택

- Backend: Spring Framework
- Database: SQLite
- Build Tool: Maven
- IDE: Eclipse

## 프로젝트 구조

```
project1/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── kopo/
│   │   │           └── project1/
│   │   │               ├── HomeController.java  # 메인 컨트롤러
│   │   │               ├── DB.java             # 데이터베이스 처리
│   │   │               ├── User.java           # 사용자 모델
│   │   │               └── ApiController.java  # API 처리
│   │   ├── resources/
│   │   └── webapp/
│   └── test/
├── target/
├── pom.xml
└── README.md
```

## 실행 방법 (Eclipse)

### 1. 프로젝트 임포트
1. Eclipse IDE 실행
2. File > Import > Maven > Existing Maven Projects
3. 프로젝트 루트 디렉토리 선택
4. pom.xml 파일이 자동으로 인식되어 프로젝트가 임포트됨

### 2. 프로젝트 설정
1. 프로젝트 우클릭 > Properties
2. Java Build Path > Libraries에서 JDK 버전 확인
3. Project Facets에서 다음 항목들이 체크되어 있는지 확인:
   - Dynamic Web Module
   - Java
   - JavaScript

### 3. 데이터베이스 설정
1. SQLite 데이터베이스 파일 위치 확인
   - 기본 경로: `c:/Users/SMART-01/sts-workspace/tomcat.sqlite`
   - DB.java 파일에서 경로 수정 가능

### 4. 서버 설정 및 실행
1. 프로젝트 우클릭 > Run As > Maven Build
   - Goals: `clean install`
   - Run 버튼 클릭

2. 프로젝트 우클릭 > Run As > Run on Server
   - 서버가 없다면 새로 생성
   - Apache Tomcat 서버 선택
   - 서버 포트 설정 (기본: 8080)
   - Finish 클릭

### 5. 접속 방법
- 웹 브라우저에서 `http://localhost:8080/project1` 접속

## 주요 API 엔드포인트

- `/` : 메인 페이지
- `/join` : 회원가입 페이지
- `/join_action` : 회원가입 처리
- `/login` : 로그인 페이지
- `/login_action` : 로그인 처리
- `/mypage` : 마이페이지
- `/user_list` : 사용자 목록 (관리자)
- `/user_update` : 사용자 정보 수정
- `/logout` : 로그아웃

## 주의사항

1. 데이터베이스 설정
   - SQLite 데이터베이스 파일 경로가 올바른지 확인
   - 데이터베이스 파일에 대한 쓰기 권한 확인

2. 서버 설정
   - 포트 충돌 여부 확인
   - 서버 메모리 설정 확인

## 문제 해결

1. 빌드 오류
   - Maven Update 실행 (프로젝트 우클릭 > Maven > Update Project)
   - 프로젝트 Clean (Project > Clean...)

2. 서버 실행 오류
   - 서버 로그 확인
   - 포트 충돌 확인
   - 데이터베이스 연결 상태 확인

3. 데이터베이스 오류
   - 데이터베이스 파일 권한 확인
   - 데이터베이스 파일 경로 확인
   - SQLite JDBC 드라이버 확인
