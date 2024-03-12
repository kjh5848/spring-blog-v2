package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@AllArgsConstructor
public class BoardpersistRepository {
    private final EntityManager em;

    @Transactional
    public Board save(Board board) {
        em.persist(board);
        //board -> 영속객체
        return board;
    }

}
