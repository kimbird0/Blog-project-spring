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
    private final BCryptPasswordEncoder encoder;    //비밀번호를 db에 저장하기 전에 암호화 하는데에 사용하느 인스턴스

    //회원가입
    @Transactional
    public void register(User user) {
        String rawPassword = user.getPassword();                //유저가 설정한 비밀번호
        String encPassword = encoder.encode(rawPassword);       //비밀번호 암호화
        user.setRoleAndEncPassword(RoleType.USER, encPassword); //암호화된 비밀번호와 기본 사용자 역할 설정
        userRepository.save(user);                              //DB에 user 저장
    }


}
