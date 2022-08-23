package jpaboard.myboard.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BoardForm {
    private Long id;
    private String title;
    private String content;

    public BoardForm(){

    }

    public BoardForm(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
