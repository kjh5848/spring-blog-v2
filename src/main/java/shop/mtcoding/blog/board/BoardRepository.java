package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    @Transactional//더티채킹
    public void updateById(Integer id, String title, String content) {
        Board board = findById(id);
        board.setTitle(title);
        board.setContent(content);
    }

    public Board findById(Integer id) {
        Board board = em.find(Board.class, id);
        return board;
    }

    @Transactional
    public Board save(Board board) {
        em.persist(board);
        return board;
    }

    public List<Board> findAll() {
        String q = """
                select * from board_tb order by id desc
                """;
        return em.createNativeQuery(q, Board.class).getResultList();
    }

}
