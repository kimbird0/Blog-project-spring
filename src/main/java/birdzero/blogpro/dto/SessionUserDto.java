package birdzero.blogpro.dto;

import birdzero.blogpro.model.RoleType;
import birdzero.blogpro.model.User;
import lombok.Getter;

import java.io.Serializable;
@Getter
public class SessionUserDto implements Serializable {
    private Long id;
    private String username;
    private String email;
    private String password;
    private RoleType role;

    public SessionUserDto(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
    }
}
