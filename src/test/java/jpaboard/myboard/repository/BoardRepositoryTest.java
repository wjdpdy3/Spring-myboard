package jpaboard.myboard.repository;

import jpaboard.myboard.domain.Board;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
public class BoardRepositoryTest {

    @Autowired BoardRepository boardRepository;
    @Autowired EntityManager em;

    @Test
    public void save_test() {
        // Create
        Board board = new Board("제목", "내용");
        boardRepository.save(board);
        Board findBoard = em.find(Board.class, board.getId());
        assertThat(findBoard).isEqualTo(board);
    }

    @Test
    public void find_test() throws Exception{
        //given
        Board board1 = new Board("제목1", "내용1");
        Board board2 = new Board("제목2", "내용2");

        //when
        em.persist(board1);
        em.persist(board2);

        //then
        //단건 조회
        Board findBoard = boardRepository.findOne(board1.getId());
        assertThat(board1).isEqualTo(findBoard);

        //전부 조회
        List<Board> findBoards = boardRepository.findAllOld();
        for (Board board : findBoards) {
            System.out.println("board = " + board);
        }
    }

    @Test
    public void delete_test() throws Exception{
        //given
        Board board = new Board("제목", "내용");
        //when
        em.persist(board);
        Long count = em.createQuery("select count(b) from Board b", Long.class)
                .getSingleResult();
        //then
        boardRepository.remove(board.getId());
        Long countResult = em.createQuery("select count(b) from Board b", Long.class)
                .getSingleResult();
        assertThat(countResult).isEqualTo(count-1);
    }

    @Test
    public void 검색조건_find() throws Exception{
        //given
        Board board1 = new Board("어린왕자", "1234");
        Board board2 = new Board("Java의 정석", "1111");
        em.persist(board1);
        em.persist(board2);

        //when
        List<Board> findBoards = boardRepository.findAll(new BoardSearch("어린왕자", BoardSearchCond.TITLE));

        //then
        assertThat(findBoards.size()).isEqualTo(1);
        assertThat(findBoards.get(0)).isEqualTo(board1);

    }

    @Test
    public void orderFind() throws Exception{
        //given
        BoardSearch boardSearch = new BoardSearch("제목", BoardSearchCond.TITLE, BoardOrderCond.OLD);
        //when
        List<Board> result = boardRepository.findAll(boardSearch);
        //then
        for (Board board : result) {
            System.out.println("board = " + board);
        }
    }
}