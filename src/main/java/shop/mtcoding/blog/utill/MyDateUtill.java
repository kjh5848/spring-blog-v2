package shop.mtcoding.blog.utill;

import org.apache.commons.lang3.time.DateFormatUtils;
import java.sql.Date;
import java.sql.Timestamp;

public class MyDateUtill {

    public static String timestampFormat(Timestamp time) {
        // Timestamp를 Date 객체로 변환
        Date currentDate = new Date(time.getTime());
        // 원하는 포맷으로 날짜를 변환
        return DateFormatUtils.format(currentDate, "yyyy-MM-dd HH:mm");

    }
}
