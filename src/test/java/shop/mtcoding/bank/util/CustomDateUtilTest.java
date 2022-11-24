package shop.mtcoding.bank.util;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class CustomDateUtilTest {
    @Test
    public void _test() throws Exception {
        // given
        LocalDateTime localDateTime = LocalDateTime.now();
        // when
        String result = CustomDateUtil.toStringFormat(localDateTime);
        System.out.println("디버그 : " + result);
        // then

    }
}
