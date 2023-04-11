package birdzero.blogpro.repository;

import birdzero.blogpro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface UserRepository extends JpaRepository<User, Long> { //data JPA 인터페이스 (엔티티 유형 User, 키 유형 Long)
                                                                    //CRUD 작업을 위한 일련의 메서드를 상속
    User findByUsername(String username);   //username 으로 유저 엔티티를 검색

}
