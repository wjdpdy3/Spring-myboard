package jpaboard.myboard;

import jpaboard.myboard.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * 총 게시물 3개
 * board1
 *  --> title : 제목1
 *  --> content : 이것은 내용입니다1.
 *  board2
 *  --> title : 제목2
 *  --> content : 이것은 내용입니다2.
 *  board3
 *  --> title : 제목3
 *  --> content : 이것은 내용입니다3.
 */

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{
        private final EntityManager em;
        public void dbInit1(){
            Board board1 = createBoard("제목1", "이것은 내용입니다1.");
            em.persist(board1);

            Board board2 = createBoard("제목2", "이것은 내용입니다2.");
            em.persist(board2);

            Board board3 = createBoard("제목3", "이것은 내용입니다3.");
            em.persist(board3);

        }

        private Board createBoard(String title, String content) {
            Board board = new Board(title, content);
            return board;
        }
    }
}
