package birdzero.blogpro.service;

import birdzero.blogpro.model.RoleType;
import birdzero.blogpro.model.User;
import birdzero.blogpro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncodingService passwordEncodingService;

    //회원가입
    @Transactional
    public void register(User user) {
        String rawPassword = user.getPassword();
        String encPassword = passwordEncodingService.encode(rawPassword);
        user.setRoleAndEncPassword(RoleType.USER, encPassword);
        userRepository.save(user);
    }

}
