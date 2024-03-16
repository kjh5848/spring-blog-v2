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

    public Reply findById(Integer id) {
        Reply reply = em.find(Reply.class, id);
        return reply;
    }


    @Transactional
    public void deleteById(Integer id) {
        em.createQuery("delete from Reply r where r.id=:id")
                .setParameter("id", id)
                .executeUpdate();
    }


    @Transactional
    public Reply save(Reply reply) {
        em.persist(reply);
        return reply;
    }


    public List<Reply> findByReplyId(Integer id) {
        return em.createQuery("select r from Reply r join fetch r.board join fetch r.user where r.board.id = :id")
                .setParameter("id", id)
                .getResultList();
    }

}
