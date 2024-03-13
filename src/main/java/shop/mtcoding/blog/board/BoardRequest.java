package shop.mtcoding.blog.board;

import lombok.Data;
import lombok.NoArgsConstructor;

public class BoardRequest {

    @NoArgsConstructor
    @Data
    public static class UpdateDTO {
        private String username;
        private String title;
        private String content;

        public UpdateDTO(String username, String title, String content) {
            this.username = username;
            this.title = title;
            this.content = content;
        }

        public Board toEntity() {
            return new Board(username, title, content);
        }

    }

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
