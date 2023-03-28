package birdzero.blogpro.controller.api;

import birdzero.blogpro.config.auth.PrincipalDetail;
import birdzero.blogpro.dto.ResponseDto;
import birdzero.blogpro.model.Board;
import birdzero.blogpro.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardApiController {
    private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
        boardService.register(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);
    }
}
