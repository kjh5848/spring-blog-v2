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
    public Board save(Board board) {
        em.persist(board);
        //board -> 영속객체
        return board;
    }

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


    public List<Board> findAll() {
        String q = """
                select * from board_tb order by id desc
                """;
        return em.createNativeQuery(q, Board.class).getResultList();
    }

    @Transactional
    public Board update(Integer id, Board board) {
        em.persist(board);
        //board -> 영속객체
        return board;
    }



//    @Transactional
//    public void save(String username, String tiile, String content) {
//        String q = """
//                insert into board_tb(username, title, content) values(?,?,?,now())
//                """;
//        em.createNativeQuery(q, Board.class)
//                .setParameter(1, username)
//                .setParameter(2, tiile)
//                .setParameter(3, content)
//                .executeUpdate();
//    }
}
