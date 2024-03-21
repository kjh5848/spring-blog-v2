package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Repository
@RequiredArgsConstructor
public class BoardQueryRepository {
    private final EntityManager em;

    public void findAllWithReplyCount() {
        em.createQuery("");
    }
}
