package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BoardRequest {
    @Data
    public static class UpdateDTO {
        private String title;
        private String content;
    }


        @Data
    public static class SaveDTO {
        private String title;
        private String content;

        public Board toEntity() {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .build();
        }
    }
}
