package birdzero.blogpro.config.auth;

import birdzero.blogpro.dto.SessionUserDto;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
public class PrincipalDetail implements UserDetails, OAuth2User {

    private SessionUserDto user;
    private Map<String,Object> attributes;      //OAuth2 provider로 부터 반환된 추가 속성 저장 필드
    public PrincipalDetail(SessionUserDto user){
        this.user = user;               //유저 필드만 초기화
    }

    public PrincipalDetail(SessionUserDto user, Map<String, Object> attributes){
        this.user = user;
        this.attributes = attributes;   //유저, 속성 필드 모두 초기화
    }
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }           //위 4개 메서드는 사용자 계정의 상태를 나타내는 부울 값을 반환
                                                          //현재 값은 모두 true로 계정 활성 상태

    //UserDetails의 일부, 사용자에게 할당된 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(()->{
            return "ROLE_"+user.getRole();  //사용자 역할로 구성된 문자열 반환 (ROLE_ 접두사는 Spring Security의 일반적인 규칙)
        });
        return collectors;                  //사용자의 역할을 나타내는 단일 GrantedAuthority 객체를 포함하는 collectors list를 반환
    }

    //OAuth2 provider의 추가 속성 맵을 반환
    @Override
    public Map<String, Object> getAttributes(){
        return attributes;
    }

    //사용자의 고유 식별자 반환
    @Override
    public String getName(){
        return null;
    }

}
