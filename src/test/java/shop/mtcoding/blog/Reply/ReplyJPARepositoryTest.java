package shop.mtcoding.blog.Reply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 1. One 관계는 조인하고, Many 관계는 조회를 한번더 하기-> DTO 담기
 * 2. Many 관계를 양방향 맵핑하기
 * 3. 어떤 전략을 선택해야할지 결정할 수 있어야 한다.
 */
@DataJpaTest
class ReplyJPARepositoryTest {
    @Autowired
    private ReplyJPARepository replyJPARepository;





}