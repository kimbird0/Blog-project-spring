package birdzero.blogpro.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardViewController {

    public String index(){
        return "index";
    }

    @GetMapping("/form/saveForm")
    public String saveForm(){
        return "/form/saveForm";
    }
}
