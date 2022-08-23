# Spring-myboard

## version1

## 소개
  - 간단한 CRUD 기능을 하는 게시판입니다.
  
### 기능 설명
  - 게시물을 등록 할 수 있다.
  - 게시물 리스트를 확인할 수 있다.
  - 게시물 리스트에서 게시물을 클릭하여 내용을 확인할 수 있다.
  - 게시물을 삭제할 수 있다.
  - 게시물을 수정할 수 있다.
  
### File Structure
<pre>
 ─src
    ├─main
    │  ├─generated
    │  ├─java
    │  │  └─jpaboard
    │  │      └─myboard
    │  │          ├─controller
    │  │          ├─domain
    │  │          ├─repository
    │  │          └─service
    │  └─resources
    │      ├─static
    │      │  ├─css
    │      │  └─js
    │      └─templates
    │          ├─board
    │          └─fragments
    └─test
        └─java
            └─jpaboard
                └─myboard
                    └─repository
</pre>

### 개발 환경
  - Intellij
  - SpringBoot 2.7.2
  - H2 Database 2.1.214
  - Java11
  - BootStrap 4.3.1
  
### 시연 영상
![JPA-Board-Chrome-2022-08-23-16-36-10](https://user-images.githubusercontent.com/57008901/186101223-a6f35ffb-2995-4aab-9845-728f5aab404b.gif)

