package birdzero.blogpro.controller.view;

import birdzero.blogpro.model.Board;
import birdzero.blogpro.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
