package jpaboard.myboard.repository;

 import com.querydsl.core.types.OrderSpecifier;
 import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpaboard.myboard.domain.Board;
import jpaboard.myboard.domain.QBoard;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

    public List<Board> findAllOld(){
        List<Board> boards = em.createQuery("select b from Board b", Board.class)
                .getResultList();
        return boards;
    }

    //검색 조건
    public List<Board> findAll(BoardSearch boardSearch){
        JPAQueryFactory query = new JPAQueryFactory(em);
        QBoard board = QBoard.board;

        return query.select(board)
                .from(board)
                .where(isSearchable(boardSearch.getBoardSearchCond(), boardSearch.getKeyword()))
                .orderBy(isOrderable(boardSearch.getBoardOrderCond()))
                .limit(1000)
                .fetch();
    }

    private OrderSpecifier<?> isOrderable(BoardOrderCond boardOrderCond) {
        if (boardOrderCond == BoardOrderCond.NEW){
            return QBoard.board.localDateTime.desc();
        }
        else if(boardOrderCond == BoardOrderCond.OLD){
            return QBoard.board.localDateTime.asc();
        }
        else if(boardOrderCond == BoardOrderCond.VIEWCOUNT){
            return QBoard.board.viewCount.desc();
        }
        return QBoard.board.localDateTime.desc();
    }


    private BooleanExpression isSearchable(BoardSearchCond boardSearchCond, String keyword) {
        if(boardSearchCond == null){
            return null;
        }
        else if (boardSearchCond == BoardSearchCond.TITLE){
            return keywordLike(keyword);
        }
        else{
            return null;
        }

    }

    private BooleanExpression keywordLike(String keyword) {
        if(!StringUtils.hasText(keyword)){
            return null;
        }
        return QBoard.board.title.contains(keyword);
    }


    /**
     * Delete
     */
    public void remove(Long boardId){
        Board board = findOne(boardId);
        em.remove(board);
    }

}
