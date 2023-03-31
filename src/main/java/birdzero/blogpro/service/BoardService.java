package birdzero.blogpro.service;

import birdzero.blogpro.model.Board;
import birdzero.blogpro.model.User;
import birdzero.blogpro.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional
    public void register(Board board, User user){
        board.setterCountAndUser(0,user);
        boardRepository.save(board);
    }

    public List<Board> boardList(){
        return boardRepository.findAll();
    }
    
    public Board readDetail(int id){
        return boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("게시물을 찾을 수 없습니다.");
                });
    }

}
