package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        private String username;
        private String title;
        private String content;

        public Board toEntity() {
            return new Board(username, title, content);
        }
    }
}
