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
@Entity //db 테이블
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //기본키, db에서 자동으로 생성되어야함
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column(nullable = false, length = 100) //해싱을 위해 더 길게 설정
    private String password;

    @Column(nullable = false, length = 50)
    private String email;


    @CreationTimestamp
    private Timestamp createDate;           //사용자가 생성된 시간

    @Enumerated(EnumType.STRING)            //enum값이 db에 문자열로 저장돼야함
    private RoleType role;

    private String provider;
    private String providerId;
    public void setRoleAndEncPassword(RoleType role, String password){
        this.role = role;
        this.password = password;
    }




}
