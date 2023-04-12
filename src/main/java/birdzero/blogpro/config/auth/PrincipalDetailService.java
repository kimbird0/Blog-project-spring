package birdzero.blogpro.config.auth;

import birdzero.blogpro.dto.SessionUserDto;
import birdzero.blogpro.model.User;
import birdzero.blogpro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    //실제 비밀번호 확인은 Spring Security의 인증 메커니즘에 의해 처리됨
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username);        //username만 db에 있는지 확인하면 됨
        if(user == null){                                           //사용자가 없으면
            throw new UsernameNotFoundException(username);
        }
        SessionUserDto sessionUserDto = new SessionUserDto(user);   //직렬화 가능한 user DTO 생성
        return new PrincipalDetail(sessionUserDto);
    }

}
