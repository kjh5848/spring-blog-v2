package shop.mtcoding.blog.Reply;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Table(name = "reply_tb")
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    @Transient
    private boolean isReplyOwner;

    @CreationTimestamp // pc -> db (날짜주입)
    private Timestamp createdAt;

    @Builder
    public Reply(Integer id, String comment, User user, Board board, Timestamp createdAt) {
        this.id = id;
        this.comment = comment;
        this.user = user;
        this.board = board;
        this.createdAt = createdAt;
    }


    //    //댓글 주인 여부
    public void checkReplyOwner(User sessionUser) {
        if (sessionUser != null) {
            if (sessionUser.getId() == getUser().getId()) {
                isReplyOwner = true;
                setReplyOwner(isReplyOwner);
            }
        }
    }


}
