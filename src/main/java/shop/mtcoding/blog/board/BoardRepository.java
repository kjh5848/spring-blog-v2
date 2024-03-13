package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    public Board findByIdJoinUser(int id) {
        return em.createQuery("select b from Board b join fetch b.user u where b.id = :id", Board.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Board findById(Integer id) {
        // id, titile, content. user_id(이질감), created_at
        Board board = em.find(Board.class, id);
        return board;
    }
}
