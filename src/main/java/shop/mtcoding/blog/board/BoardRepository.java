package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.user.User;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    @Transactional
    public void deleteById(Integer id) {
        em.createQuery("delete from Board b where b.id =:id")
                .setParameter("id", id)
                .executeUpdate();
    }


    public List<Board> findAllV2() {
        String q1 = """
                select b from Board b order by b.id desc
                 """;
        List<Board> boardList = em.createQuery(q1, Board.class)
                .getResultList();

        String q2 = """
                SELECT u FROM User u WHERE u.id IN (:ids)
                """;
        List<User> userList = em.createQuery(q2, User.class)
                .setParameter("ids", Arrays.asList(1, 2, 3))
                .getResultList();

        List<Board> matchedBoards = boardList.stream()
                .filter(board -> userList.stream().anyMatch(user -> user.getId() == board.getUser().getId()))
                .collect(Collectors.toList());

        return matchedBoards;
    }


    public List<Board> findAll() {
        return em.createQuery("select b from Board b order by b.id desc ", Board.class)
                .getResultList();
    }

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

    //퍼시스트는 내가 만든 로직이 아니기 때문에 테스트 안함
    @Transactional
    public void save(Board board) {
        em.persist(board);
    }
}
