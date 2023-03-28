package birdzero.blogpro.repository;

import birdzero.blogpro.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
}
