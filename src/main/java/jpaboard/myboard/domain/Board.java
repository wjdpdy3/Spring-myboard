package jpaboard.myboard.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "title"})
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String title; //제목
    private String content; //내용


    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 제목, 내용 수정
    public Board changeBoard(String title, String content) {
        this.title = title;
        this.content = content;
        return this;
    }
}
