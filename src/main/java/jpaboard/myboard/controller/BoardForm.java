package jpaboard.myboard.controller;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter
public class BoardForm {
    private Long id;
    private String title;
    private String content;
    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private int viewCount;
    private String convertedDate;

    public BoardForm(){

    }

    public BoardForm(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public BoardForm(Long id, String title,LocalDate localDate, int viewCount) {
        this.id = id;
        this.title = title;
        this.localDate = localDate;
        this.viewCount = viewCount;
    }

    public BoardForm(Long id, String title, String content, LocalDateTime localDateTime, int viewCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.localDateTime = localDateTime;
        this.viewCount = viewCount;
    }

    // 변환된 날짜&시간 저장
    public void setConvertedDate(){
        this.convertedDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss"));
    }
}
