package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.user.User;

public class BoardResponse {

    @Data
    public static class DTO {
        private Integer id;
        private String title;
        private String content;

        public DTO(Board board) {
            this.id =board.getId();
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
