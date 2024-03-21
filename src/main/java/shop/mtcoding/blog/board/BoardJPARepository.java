package shop.mtcoding.blog.board;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.mtcoding.blog.Reply.Reply;

import java.util.List;
import java.util.Optional;

public interface BoardJPARepository extends JpaRepository<Board, Integer> {
    @Query("select b from Board b join fetch b.user u where b.id = :id")
    Optional<Board> findByJoinUser(@Param("id") int id);



    @Query(value = "SELECT new com.example.dto.BoardDTO(bt.id, bt.title, bt.content, bt.user_id, (SELECT COUNT(id) FROM reply_tb WHERE board_id = bt.id)) FROM board_tb bt", nativeQuery = true)
    List<BoardResponse.CountDTO> findAllWithReplyCount();
}
