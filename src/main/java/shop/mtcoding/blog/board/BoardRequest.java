package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
