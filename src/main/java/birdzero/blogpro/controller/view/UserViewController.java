package birdzero.blogpro.controller.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//view 컨트롤러와 api 컨트롤러를 분리해야함
//책임분리및 가독성, 테스트 용이
@Controller
@RequiredArgsConstructor
public class UserViewController {


    @GetMapping("/auth/signupForm")
    public String signupForm(){
        return "/form/signupForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm(){
        return "/form/loginForm";
    }



}
