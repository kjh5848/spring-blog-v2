package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.utill.MyDateUtill;
import java.sql.Timestamp;

@NoArgsConstructor
@Entity(name = "board_tb")
@Table
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String title;
    private String content;

    @CreationTimestamp // pc -> db(날짜주입)
    private Timestamp createdAt;

    public String getTime() {
        return MyDateUtill.timestampFormat(createdAt);
    }

    public Board(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }
}
