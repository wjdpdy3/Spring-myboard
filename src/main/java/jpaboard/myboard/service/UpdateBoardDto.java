package jpaboard.myboard.service;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateBoardDto {

    private String title;
    private String content;

    public UpdateBoardDto() {}

    public UpdateBoardDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
