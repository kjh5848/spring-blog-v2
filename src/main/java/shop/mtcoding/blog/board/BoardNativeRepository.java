package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@AllArgsConstructor
public class BoardNativeRepository {
    private final EntityManager em;

    @Transactional
    public void deleteById(Integer id) {
        String q = """
                delete from board_tb where id = ?
                """;
        em.createNativeQuery(q, Board.class)
                .setParameter(1, id)
                .executeUpdate();
    }


    public Board findById(int id) {
        String q = """
                select * from board_tb where id = ?
                """;
        return (Board) em.createNativeQuery(q, Board.class)
                .setParameter(1, id)
                .getSingleResult();
    }


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


    public List<Board> findAll() {
        String q = """
                select * from board_tb order by id desc
                """;
        return em.createNativeQuery(q, Board.class).getResultList();
    }
}
