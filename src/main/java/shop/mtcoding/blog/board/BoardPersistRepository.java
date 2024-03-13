package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardPersistRepository {
    private final EntityManager em;


    public List<Board> findAll() {
        Query query = em.createQuery("select b from board_tb b order by b.id desc", Board.class);
        return query.getResultList();
    }


    @Transactional
    public Board save(Board board) {
        em.persist(board);
        //board -> 영속객체
        return board;
    }

}
