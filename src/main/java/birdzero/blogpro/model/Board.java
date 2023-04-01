package birdzero.blogpro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    public void setterCountAndUser(int count, User user) {
        this.count = count;
        this.user = user;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false,length = 100)
    private String title;

    @ColumnDefault("0")
    private int viewCount;

    @Lob
    private String content;

    @ColumnDefault("0")
    private int count;

    @CreationTimestamp
    private Timestamp createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    private User user;

    @OneToMany(mappedBy = "board")
    private List<Reply> replies;

    public void updateBoard(String title, String content){
        this.title =title;
        this.content =content;
    }

    public Board updateViewCount(int viewCount){
        this.viewCount = viewCount+1;
        return this;
    }

}
