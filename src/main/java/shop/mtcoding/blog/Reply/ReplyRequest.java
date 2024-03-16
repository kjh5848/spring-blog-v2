package shop.mtcoding.blog.Reply;

import lombok.AllArgsConstructor;
import lombok.Data;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.user.User;

public class ReplyRequest {
    @AllArgsConstructor
    @Data
    public static class SaveDTO {
        private String comment;

        public Reply toEntity(User user, Board board) {
            return Reply.builder()
                    .user(user)
                    .board(board)
                    .comment(comment)
                    .build();

        }
    }

}
