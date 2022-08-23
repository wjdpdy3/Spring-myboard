package jpaboard.myboard.repository;

import jpaboard.myboard.domain.Board;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    /**
     * Create
     */
    public Long save(Board board){
        em.persist(board);
        return board.getId();
    }

    /**
     * Read
     */
    public Board findOne(Long boardId){
        Board board = em.find(Board.class, boardId);
        return board;
    }

    public List<Board> findAll(){
        List<Board> boards = em.createQuery("select b from Board b", Board.class)
                .getResultList();
        return boards;
    }

    /**
     * Delete
     */
    public void remove(Long boardId){
        Board board = findOne(boardId);
        em.remove(board);
    }

}
