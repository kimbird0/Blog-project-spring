package birdzero.blogpro.controller.api;

import birdzero.blogpro.dto.ResponseDto;
import birdzero.blogpro.model.User;
import birdzero.blogpro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//view 컨트롤러와 api 컨트롤러를 분리해야함
//책임분리및 가독성, 테스트 용이
@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/auth/signup")
    public ResponseDto<Integer> save(@RequestBody User user){
        userService.register(user); //회원가입 성공 시 1, 실패 시 -1 반환.
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
