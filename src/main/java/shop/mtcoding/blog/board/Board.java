package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import shop.mtcoding.blog.utill.MyDateUtill;

import java.sql.Timestamp;

@NoArgsConstructor
@Entity
@Table(name = "board_tb")
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String title;
    private String content;
    private Timestamp createdAt;



    public String getTime() {
        return MyDateUtill.timestampFormat(createdAt);
    }

}
