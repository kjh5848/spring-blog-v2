package shop.mtcoding.blog.Board;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

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

}
