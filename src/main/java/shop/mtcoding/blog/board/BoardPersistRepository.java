package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardPersistRepository {
    private final EntityManager em; // DI

    @Transactional // 고립성
    public void update(Integer id, BoardRequest.UpdateDTO reqDTO) {
        Board board = findById(id);
        board.update(reqDTO);
        //더티체킹
    }

    @Transactional
    public void deleteById(Integer id) {
        em.createQuery("delete from Board b where b.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    public Board findById(int id) {
        Board board = em.find(Board.class, id); // (클래스, 프라이머리키)
        return board;
    }

    public List<Board> findAll() {
        Query query = em.createQuery("SELECT b FROM Board b  ORDER BY b.id DESC", Board.class); // JPQL에 대한 연습이 필요하다.
        return query.getResultList();
    }

    @Transactional // 고립성
    public Board save(Board board) {
        // 1. 비영속 객체
        em.persist(board); // 전달 (만약 pk를 가지고 들어간다면? update가 된다!)
        // 2. board -> 영속 객체
        return board;
    }
}