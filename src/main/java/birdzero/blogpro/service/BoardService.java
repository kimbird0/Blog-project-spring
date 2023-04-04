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
        board.setterUser(user);
        boardRepository.save(board);
    }

    public List<Board> boardList(){
        return boardRepository.findAll();
    }

    @Transactional
    public Board readDetail(int id){
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("게시물을 찾을 수 없습니다.");
                });
        board.updateViewCount(board.getViewCount());
        System.out.println("게시물 조회수 = " + board.getViewCount());
        return board;
    }
    @Transactional
    public void delete(int id){
        boardRepository.deleteById(id);
    }
    @Transactional
    public void updateBoard(int id, Board requestBoard){
        Board findBoard = boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("게시글 수정에 실패했습니다.");
                });
        findBoard.updateBoard(requestBoard.getTitle(),requestBoard.getContent());
    }


}
