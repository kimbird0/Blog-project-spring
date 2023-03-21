package birdzero.blogpro.repository;

import birdzero.blogpro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<User, Integer> {

}
