package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    @Transactional
    public void deleteById(Integer id) {
        em.createQuery("delete from Board b where b.id=:id")
                .setParameter("id",id)
                .executeUpdate();
    }

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
                 select b from Board b order by b.id desc
                 """;
        List<Board> boardList =  em.createQuery(q, Board.class).getResultList();

        List<Integer> ids = boardList.stream()
                .map(Board::getId)
                .toList();

        String q1 = """
                SELECT u FROM User u WHERE u.id IN (:ids)
                """;
        List<User> userList = em.createQuery(q1, User.class)
                .setParameter("ids",ids)
                .getResultList();

        List<Board> machBoardList = boardList.stream()
                .filter(board -> userList.stream().anyMatch(user -> user.getId() == board.getUser().getId()))
                .toList();

        return machBoardList;
    }

}
