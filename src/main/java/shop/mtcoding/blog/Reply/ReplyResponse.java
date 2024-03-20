package shop.mtcoding.blog.Reply;

import lombok.Data;
import shop.mtcoding.blog.board.Board;

public class ReplyResponse {
    @Data
    public static class DTO {
        private Integer boardId;
        private String comment;

        public DTO(Reply reply) {
            this.boardId = reply.getBoard().getId();
            this.comment = reply.getComment();

        }
    }
}
