package birdzero.blogpro.controller.api;

import birdzero.blogpro.config.auth.PrincipalDetail;
import birdzero.blogpro.dto.ResponseDto;
import birdzero.blogpro.dto.SaveBoardDto;
import birdzero.blogpro.model.Board;
import birdzero.blogpro.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardApiController {
    private final BoardService boardService;

    //게시물 작성
    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody SaveBoardDto saveBoardDto, @AuthenticationPrincipal PrincipalDetail principal){
        //데이터 형식(이 경우 JSON)을 애플리케이션에서 사용할 수 있는 개체로 변환(deserialize)
        boardService.register(saveBoardDto.toEntity(), principal.getUser().getId()); //새 보드 저장
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);       //HTTP 상태 코드 200과 데이터 값 1 반환
    }
    //게시물 삭제
    @DeleteMapping("/api/board/{id}")                                               //id는 경로 변수로 제공
    public ResponseDto<Integer> deleteById(@PathVariable int id){
        boardService.delete(id);                                                    //보드 삭제
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);      //HTTP 상태 코드 200과 데이터 값 1 반환
    }
    //게시물 수정
    @PutMapping("/api/board/{id}")                                                  //id는 경로 변수로 제공
    public ResponseDto<Integer> update(@PathVariable int id, @RequestBody Board board){
        boardService.updateBoard(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(),1);       //HTTP 상태 코드 200과 데이터 값 1 반환
    }

}
