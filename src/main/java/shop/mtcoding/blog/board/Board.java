package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.Reply.Reply;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "board_tb")
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @CreationTimestamp
    private Timestamp createdAt;

    @OrderBy("id DESC ")
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Reply> replies = new ArrayList<>();

    @Transient
    private boolean isBoardOwner;

    @Builder
    public Board(Integer id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }

    //게시글의 주인여부 체크
    public void checkBoardOwner(User sessionUser) {
        if (sessionUser != null) {
            if (sessionUser.getId() == getUser().getId()) {
                isBoardOwner = true;
                setBoardOwner(isBoardOwner);
            }
        }
    }


//    public Board isOwer(User sessionUser) {
//        if (sessionUser != null) isOwer = false;
//        sessionUser.getId() == getUser().getId();
//    }
}
