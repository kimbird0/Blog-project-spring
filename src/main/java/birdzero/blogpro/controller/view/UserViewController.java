package birdzero.blogpro.controller.view;

import birdzero.blogpro.dto.ResponseDto;
import birdzero.blogpro.model.User;
import birdzero.blogpro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//view 컨트롤러와 api 컨트롤러를 분리해야함
//책임분리및 가독성, 테스트 용이
@Controller
@RequiredArgsConstructor
public class UserViewController {


    @GetMapping("/user/signupForm")
    public String joinForm(){
        return "/signupForm";
    }

    @GetMapping("/user/loginForm")
    public String loginForm(){
        return "/loginForm";
    }

    //회원가입

}
