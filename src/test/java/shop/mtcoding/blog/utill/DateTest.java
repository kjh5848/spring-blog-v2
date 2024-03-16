package shop.mtcoding.blog.utill;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.board.BoardNativeRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;

@Import(BoardNativeRepository.class)
@DataJpaTest
public class DateTest {

    @Test
    public void timestampFormat() {
        //given
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        //when
        String createdAt = MyDateUtill.timestampFormat(currentTimestamp);

        //then
        System.out.println("createdAt_test = " + createdAt);
    }

    @Test
    public void format_test(){
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());

        // Timestamp를 Date 객체로 변환
        Date currentDate = new Date(currentTimestamp.getTime());

        // 원하는 포맷으로 날짜를 변환
        String formattedDate = DateFormatUtils.format(currentDate, "yyyy-MM-dd HH:mm");

        // 포맷된 날짜 출력
        System.out.println("Formatted Date_test: " + formattedDate);
    }
}
