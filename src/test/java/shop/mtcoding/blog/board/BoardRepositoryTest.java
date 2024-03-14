package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.user.User;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Import(BoardRepository.class)
@DataJpaTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void findAllv4_test(){
        // given
        List<Board> boardList = boardRepository.findAllV2();
        for (Board board : boardList) {
            System.out.println(board);

        }
        // when


        // then

    }

    @Test
    public void _test() {
        String q = """
                SELECT b FROM Board b 
                WHERE b.user.id IN (
                    SELECT u.id FROM User u 
                    WHERE u.id IN (:ids)
                ) ORDER BY b.id DESC
                """;

        List<Board> matchedBoards = em.createQuery(q, Board.class)
                .setParameter("ids", Arrays.asList(1, 2, 3))
                .getResultList();
        System.out.println("matchedBoards = " + matchedBoards);
    }

    @Test
    public void findAll_V2_test() {
        List<Board> boardList = em.createQuery("select b from Board b order by b.id desc", Board.class)
                .getResultList();

        List<Integer> userIds = boardList.stream()
                .map(board -> board.getUser().getId())
                .distinct()
                .collect(Collectors.toList());

        List<User> userList = em.createQuery("select u from User u where u.id in :userIds", User.class)
                .setParameter("userIds", userIds) // 여기에 List<Integer> 전달
                .getResultList();

        boardList.stream().filter(board -> userList.stream().anyMatch(user -> user.getId() == board.getUser().getId()))
                .forEach(System.out::println);
    }

    @Test
    public void findAll_custom_inquery_test() {
        List<Board> boardList = boardRepository.findAll();

        int[] userIds = boardList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();

        for (int i : userIds) {
            System.out.println(i);
        }

        // select * from user_tb where id in (3,2,1,1);
        // select * from user_tb where id in (3,2,1);
    }

    @Test
    public void findAll_inQuery_test() {
        List<Board> boardList = boardRepository.findAllV2();

        int[] ids = {1, 2, 3};

        // select u from User u where u.id in (?,?);
        String q = "select u from User u where u.id in (";
        for (int i = 0; i < ids.length; i++) {
            if (i == ids.length - 1) {
                q = q + "?)";
            } else {
                q = q + "?,";
            }
        }
        System.out.println("in_test:" + q);

    }


    @Test
    public void findAll_lazyLoading_test() {
        // given
        List<Board> boardList = boardRepository.findAll();
        boardList.forEach(board -> {
            System.out.println(board.getUser().getId());
        });
        // when

        // then

    }

    @Test
    public void findAll_test() {
        // given

        // when
        List<Board> boardList = boardRepository.findAll();

        // then
        //
        int[] userIds = boardList.stream().mapToInt(board -> Math.toIntExact(board.getUser().getId())).distinct().toArray();
        for (int userId : userIds) {
            System.out.println(userId);
        }
//        boardList.stream().map(board -> board.getUser().getId()).forEach(System.out::println);

    }


    @Test
    public void findById_test() {

        int id = 1;

        boardRepository.findById(id);
    }
}