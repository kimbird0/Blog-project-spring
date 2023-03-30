package birdzero.blogpro.config.oauth;

import birdzero.blogpro.config.auth.PrincipalDetail;
import birdzero.blogpro.model.RoleType;
import birdzero.blogpro.model.User;
import birdzero.blogpro.provider.GoogleUserInfo;
import birdzero.blogpro.provider.OAuth2UserInfo;
import birdzero.blogpro.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("ClientRegistration = "+ userRequest.getClientRegistration());
        System.out.println("AccessToken = " + userRequest.getAccessToken().getTokenValue());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("Attrebutes = " + oAuth2User.getAuthorities());

        OAuth2UserInfo oAuth2UserInfo = null;

        if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
            oAuth2UserInfo =new GoogleUserInfo(oAuth2User.getAttributes());
        }else {
            System.out.println("구글 로그인만 지원합니다.");
        }
        String provider = oAuth2UserInfo.getProvider();
        String providerID = oAuth2UserInfo.getProviderId();
        String email = oAuth2UserInfo.getEmail();
        String username = provider +"_"+providerID;
        String password = encoder.encode("비밀번호");

        User userEntity = userRepository.findByUsername(username);
        if (userEntity==null){
            System.out.println("첫 로그인입니다.");
            userEntity = User.builder()
                    .username(username)
                    .email(email)
                    .password(password)
                    .role(RoleType.USER)
                    .provider(provider)
                    .providerId(providerID)
                    .build();
            userRepository.save(userEntity);
        }else{
            System.out.println("로그인 기록이 있습니다. 자동 로그인됩니다.");
        }
        return new PrincipalDetail(userEntity, oAuth2User.getAttributes());
    }
}
