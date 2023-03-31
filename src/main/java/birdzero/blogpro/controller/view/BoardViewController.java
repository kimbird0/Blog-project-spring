package birdzero.blogpro.controller.view;

import birdzero.blogpro.model.Board;
import birdzero.blogpro.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardViewController {

    private final BoardService boardService;
    @GetMapping("/")
    public String index(Model model){
        List<Board> boards = boardService.boardList();
        model.addAttribute("boards", boards);
        return "index";
    }

    @GetMapping("/form/saveForm")

    public String saveForm(){
        return "/form/saveForm";
    }
    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model){
        Board board = boardService.readDetail(id);
        model.addAttribute("board",board);
        return "/form/boardDetailForm";
    }
}
