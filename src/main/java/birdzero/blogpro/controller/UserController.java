package birdzero.blogpro.controller;

import birdzero.blogpro.dto.ResponseDto;
import birdzero.blogpro.model.User;
import birdzero.blogpro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/joinForm")
    public String joinForm(){
        return "user/joinForm";
    }

    @GetMapping("/user/loginForm")
    public String loginForm(){
        return "user/loginForm";
    }

    //회원가입
    @PostMapping("/user/join")
    public ResponseDto<Integer> save(@RequestBody User user){
        int result = userService.register(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),result);
    }

}
