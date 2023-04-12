package birdzero.blogpro.config;

import birdzero.blogpro.config.oauth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor                     //시큐리티 필터 설정,등록
@EnableMethodSecurity(prePostEnabled = true) //@PreAuthorize 및 @PostAuthorize 사용가능, 특정 주소에서 권한 및 인증을 미리 체크
public class SecurityConfig {

    private final PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**","/","/js/**","/css/**", "/img/**","/blog/**") //인증 없이 액세스할 수 있도록 지정된 경로를 구성
                .permitAll()                                                                      //모든 사용자가 지정된 경로에 액세스
                .anyRequest()       //그 외 리퀘스트는
                .authenticated()    //인증이 필요
                .and()
                .formLogin()                         //로그인 페이지의 HTML 양식을 통해 자격 증명
                .loginPage("/auth/loginForm")        //로그인 페이지 URL
                .loginProcessingUrl("/auth/login")   //로그인 양식을 제출할 URL
                .defaultSuccessUrl("/")              //로그인 성공 후 리디렉션할 기본 URL
                .failureUrl("/auth/login/error")    //로그인 실패 후 리디렉션할 URL

                .and()
                .oauth2Login()                      //OAuth2 로그인 페이지 URL
                .loginPage("/auth/loginForm")
                .userInfoEndpoint()
                .userService(principalOauth2UserService);

        return http.build();
    }
}