package shop.mtcoding.blog.Reply;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Transient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.board.Board;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReplyRepository {
    private final EntityManager em;

    @Transactional
    public Reply save(Reply reply) {
        em.persist(reply);
        return reply;
    }


    public List<Reply> findAll() {
        return  em.createQuery("select r from Reply r join fetch r.board join fetch r.user")
                .getResultList();
    }

}
