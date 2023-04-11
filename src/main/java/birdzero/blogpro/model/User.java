package birdzero.blogpro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;
import java.util.List;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 100) //해싱을 위해 더 길게 설정
    private String password;

    @Column(nullable = false, length = 50)
    private String email;


    @CreationTimestamp
    private Timestamp createDate;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    private String provider;
    private String providerId;
    public void setRoleAndEncPassword(RoleType role, String password){
        this.role = role;
        this.password = password;
    }




}
