package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity(name = "board_tb")
@Table
@Getter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String title;
    private String content;

    @CreationTimestamp
    private Timestamp createdAt;

    public Board(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }
}
