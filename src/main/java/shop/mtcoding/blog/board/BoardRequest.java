package shop.mtcoding.blog.board;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.mtcoding.blog.user.User;

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

        public Board toEntity(User user) {
            return Board.builder()
                    .title(title)
                    .content(content)
                    .user(user)
                    .build();
        }
    }
}
