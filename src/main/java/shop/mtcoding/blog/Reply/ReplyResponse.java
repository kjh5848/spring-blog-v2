package shop.mtcoding.blog.Reply;

import shop.mtcoding.blog.user.User;

public class ReplyResponse {
    public static class DTO {
        private int id;
        private String comment;
        private String username;

        public DTO(Reply reply, User sessionUser) {
            this.id = reply.getId();
            this.comment = reply.getComment();
            this.username = sessionUser.getUsername();
        }
    }
}
