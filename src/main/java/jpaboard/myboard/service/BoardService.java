package jpaboard.myboard.service;

import jpaboard.myboard.domain.Board;
import jpaboard.myboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    /**
     * 게시물 등록
     */
    @Transactional
    public Long join(Board board){
        Long saveId = boardRepository.save(board);
        return saveId;
    }

    /**
     * 게시물 조회
     */
    // 전체 조회
    public List<Board> findBoards(){
        return boardRepository.findAll();
    }
    // 단건 조회
    public Board findOne(Long boardId){
        return boardRepository.findOne(boardId);
    }

    /**
     * 게시물 삭제
     */
    @Transactional
    public void removeBoard(Long boardId){
        boardRepository.remove(boardId);
    }


    /**
     * 게시물 수정
     */
    @Transactional
    public Board updateBoard(Long boardId, UpdateBoardDto updateBoardDto) {
        Board findBoard = boardRepository.findOne(boardId);
        Board changeBoard = findBoard.changeBoard(updateBoardDto.getTitle(), updateBoardDto.getContent());
        return changeBoard;
    }
}
