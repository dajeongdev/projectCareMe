# Care Me
![image](https://user-images.githubusercontent.com/61612976/116785034-63412580-aad2-11eb-933f-ac7fffa7fa71.png)
<br />
<br />

## 개요
- **구성원 및 역할** : 백엔드 4 / 팀장 및 기획   
- **기간** : 2020.03.05 - 2020.04.21   
- **주제** : 급증하는 반려동물에 대한 섬세한 관리를 위한 반려동물 케어 서비스 웹 사이트
- **구현 목표** : Spring, MyBatis를 기반으로 자신의 반려동물에 대해 기록할 수 있는 케어일기, 그 기록을 바탕으로 전문의와 상담을 진행할 수 있는 전문의 찾기 및 상담, 그리고 회원들간의 커뮤니티 기능 제공
<br />

## 개발 환경
|개발 환경|설정|
|---|---|
|서버|Apache Tomcat 8.0|
|개발 언어|Java 1.8|
|Database|MySQL (AWS RDS)|
|개발 IDE|Spring Tool Suite 4|
|Java Framework|Spring 4.3.26|
|Persistent Framework|MyBatis 3|
|CSS Framework|Bootstrap 4.4.1|
|Javascript Library|jQuery 3.2.1|
|형상 관리|Github|
|빌드 도구|Gradle|
|협업 도구|Trello|
<br />

## 구현 기능
* 일반 회원 및 의사 회원의 답변 권한을 구분한 상담 게시판
* 이메일 인증을 통한 회원가입 및 비밀번호 변경
* 정확한 진단을 위한 케어 일기 기능
* 회원들 간의 커뮤니케이션을 위한 이미지형 펫스토리 게시판
* 등록한 펫 정보의 바탕으로 태그를 통한 전문의 찾기
* 회원 관리, 의사 승인을 위한 관리자 페이지  
<br />

## 담당 기능
### 메인 페이지
* 로그인 시 메인 페이지에서 모든 게시판에 접근할 수 있다.  
![image](https://user-images.githubusercontent.com/61612976/116785034-63412580-aad2-11eb-933f-ac7fffa7fa71.png)
<br />

### 이미지 게시판(펫스토리) 목록
* 상단(3개)에 인기글이 위치해 있으며, 나머지 글들은 최신순으로 확인할 수 있다.  
![image](https://user-images.githubusercontent.com/61612976/116784670-866ad580-aad0-11eb-9744-ff58cca76d53.png)
<br />

### 이미지 게시판(펫스토리) 상세 보기
* 해당 게시글(스토리)의 구체적인 내용을 확인할 수 있다.  
![image](https://user-images.githubusercontent.com/61612976/116784676-8b2f8980-aad0-11eb-9c30-6dd1473c919d.png)
<br />

### 이미지 게시판(펫스토리) 글 작성  
* 제목과 내용은 필수이며, 추가적으로 이미지나 해시태그를 작성할 수 있다.
![image](https://user-images.githubusercontent.com/61612976/116784663-62a78f80-aad0-11eb-9a20-1832f57d18e8.png)
<br />

### 이미지 게시판(펫스토리) 해시태그별 목록  
* 해시태그를 클릭하여 해당 해시태그를 모아놓은 목록을 확인할 수 있다.
![image](https://user-images.githubusercontent.com/61612976/116784681-8ff43d80-aad0-11eb-8708-7f196d4c4d31.png)
<br />
