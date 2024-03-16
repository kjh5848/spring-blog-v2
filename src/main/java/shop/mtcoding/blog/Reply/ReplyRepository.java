package shop.mtcoding.blog.Reply;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Transient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class ReplyRepository {
    private final EntityManager em;

    @Transactional
    public Reply save(Reply reply) {
        em.persist(reply);
        return reply;
    }

}
