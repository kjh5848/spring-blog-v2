package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.Reply.Reply;
import shop.mtcoding.blog.user.User;

import java.util.ArrayList;
import java.util.List;

public class BoardResponse {

    @Data
    public static class DetailDTO {
        private Integer id;
        private String title;
        private String content;
        private Integer userId;// 안줘도 되지만 주면 id로 주인여부를 if로 분기해서 확인할 수 있다.
        private String username; // 게시글 작성자 이름
        private boolean isOwner;
        public DetailDTO(Board board,User sessionUser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername();
            this.isOwner = false;
            if (sessionUser != null) {
                if (sessionUser.getId() == userId) {
                    isOwner = true;
                }
            }
            this.replies = board.getReplies().stream().map(reply -> new ReplyDTO(reply, sessionUser)).toList();
        }

        private List<ReplyDTO> replies = new ArrayList<>();


        @Data
        public class ReplyDTO {
            private Integer id;
            private String comment;
            private Integer userId; // 댓글 작성자 아이디
            private String username; // 댓글 작성자 이름
            private boolean isOwner;

            public ReplyDTO(Reply reply,User sessionUser) {
                this.id = reply.getId(); // lazy loading 발동
                this.comment = reply.getComment();
                this.userId = reply.getUser().getId();// lazy loading 발동
                this.username = reply.getUser().getUsername();// lazy loading 발동
                this.isOwner = false;
                if (sessionUser != null) {
                    if (sessionUser.getId() == userId) {
                        isOwner = true;
                    }
                }
            }
        }
    }

    @Data
    public static class DTO {
        private Integer id;
        private String title;
        private String content;

        public DTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
        }
    }

    @Data
    public static class MainDTO {
        private Integer id;
        private String title;

        public MainDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }
}
