# MVC

- hi there

## mvc1편 느낀점
- 고민을 정말 많이 하신거같다. 어떻게 접근해야 다들 공감하고 잘 이해할수 있을지에 대한 고민이 녹아있는강의라 감동이고 너무 좋았다.
- 자료구조를 처음 공부할때 C언어로 직접 자료구조를 구현하면서 공부했는데, 딱 처음 자료구조 공부하던 방식으로 배우니까 SpringMVC 가 이렇게 구현될수밖에 없는 이유와 특징들이 자연스럽게 느낄수 

### 이미 알고계신거고, 충분히 고려하셨겠지만 
- http파일로 만들어봤는데, postman 쓰거나 브라우저에 들어가지 않을 수 있어서 뭐랄까.. 내 머리속의 Context가 Switching 되지 않고 강의 흐름 쭉 따라갈 수 있어서 좋은거같아서 한번 말씀드려봅니다!
- 대략 내 깃헙주소


## 특징
최종 결과물을 대상으로, 이에 대한 설명이 아니라
왜 그런 결과물로 만들었는지 만들수밖어 없던 이유를 이해할수 있도록
발전과정과 함께 만들어가고, 그다음 최종 결과물의 편리한 기능까지 소개시켜주는 강의라 감동적이였습니다

## 일어날 일은 일어난다


----- 이번주


## 서버에서 응답리소스를 만드는 방법 3가지

### 1) 정적 리소스를 리턴
  - js파일, css파일, html 문서, ftp서버 를 리턴할때 정적 리소스를 사용
  - 스프링부트에서는 특정 디렉토리에 둬야 정적 리소스를 제공한다(클래스패스, static, resources ..)
    - 쉽게말해서 위 경로에 파일이 놓여져 있으면 파일을 통째로 전송해주는 개념

### 2) 뷰 템플릿 사용
  - 클라이언트의 웹브라우저에 파일로 고정되어있는 HTML 문서에서 조금더 서버에서 동적으로 실시간 변경을 해주고 싶을때 사용
  - SSR방식이라고도 부른다 모든 랜더링이 끝난 최종결과물 HTML 문서가 클라이언트에게 날라가기 때문
  - HTML 이라는 문서파일을 >> 기본골격을 HTML+Thymeleaf or JSP 등으로 만들어놓고 리턴하는 시점에 동적으로 `추가/확장/생성` 해서 완섬품을 클라이언트에 보내주는방식

### 3) 메시지 (Data only)
  - json 형식 메시지를 바디에 담아서 보냄
  - 기존의 HTML 문서를 보내주는게 아니라서, 클라이언트의 브라우저에서 데이터를 받아 랜더링해줌

