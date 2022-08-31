package jpaboard.myboard.controller;

import jpaboard.myboard.domain.Board;
import jpaboard.myboard.repository.BoardSearch;
import jpaboard.myboard.service.BoardService;
import jpaboard.myboard.service.UpdateBoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    public final BoardService boardService;

    @GetMapping("/")
    public String home(@ModelAttribute("boardSearch") BoardSearch boardSearch, Model model){
        List<Board> boards = boardService.findCondBoards(boardSearch);
        List<BoardForm> formList = boards.stream()
                .map(board -> new BoardForm(board.getId(), board.getTitle(), board.getLocalDateTime().toLocalDate(), board.getViewCount()))
                .collect(Collectors.toList());
        model.addAttribute("boards", formList);

        return "home";
    }

    @GetMapping("/new")
    public String newBoard(Model model){
        model.addAttribute("form", new BoardForm());
        return "/board/newBoard";
    }

    @PostMapping("/new")
    public String createBoard(@ModelAttribute("form") BoardForm form){
        Board board = new Board(form.getTitle(), form.getContent());

        boardService.join(board);

        return "redirect:/";
    }

    /**
     * 게시물 읽기
     */
    @GetMapping("/board/{boardId}")
    public String getBoard(@PathVariable("boardId") Long boardId, Model model){
        boardService.addViewCount(boardId);
        Board board = boardService.findOne(boardId);


        BoardForm form = new BoardForm(board.getId(), board.getTitle(), board.getContent(), board.getLocalDateTime(), board.getViewCount());
        form.setConvertedDate();

        model.addAttribute("form", form);
        return "/board/readBoard";
    }

    /**
     * 게시물 삭제
     */
    @GetMapping("/board/remove/{boardId}")
    public String removeBoard(@PathVariable("boardId") Long boardId, Model model){
        boardService.removeBoard(boardId);

        return "redirect:/";
    }

    /**
     * 게시물 수정
     */
    @GetMapping("/board/edit/{boardId}")
    public String updateBoardForm(@PathVariable("boardId") Long boardId, Model model){
        Board board = boardService.findOne(boardId);

        BoardForm form = new BoardForm(board.getId(), board.getTitle(), board.getContent());
        model.addAttribute("form", form);
        return "/board/updateBoardForm";
    }

    @PostMapping("/board/edit/{boardId}")
    public String updateItem(@PathVariable("boardId") Long boardId, @ModelAttribute("form") BoardForm form){
        boardService.updateBoard(boardId, new UpdateBoardDto(form.getTitle(), form.getContent()));

        return "redirect:/board/" + boardId;
    }
}
