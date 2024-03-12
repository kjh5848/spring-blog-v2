package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
public class BoardNativeRepository {
    private final EntityManager em;

    @Transactional
    public void save(String username, String tiile, String content) {
        String q = """
                insert into board_tb(username, title, content) values(?,?,?,now())
                """;
        em.createNativeQuery(q, Board.class)
                .setParameter(1, username)
                .setParameter(2, tiile)
                .setParameter(3, content)
                .executeUpdate();
    }

}
