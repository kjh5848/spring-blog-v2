package shop.mtcoding.blog.Reply;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReplyService {
    private final ReplyJPARepository replyJPARepository;


}
