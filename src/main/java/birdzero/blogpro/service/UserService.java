package birdzero.blogpro.service;

import birdzero.blogpro.model.User;
import birdzero.blogpro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    //회원가입
    @Transactional
    public int register(User user){
        try{
            userRepository.save(user);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

}
