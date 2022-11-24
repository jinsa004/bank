package shop.mtcoding.bank.config.exception;

import lombok.Getter;

// 일반적인 에러
@Getter
public class CustomApiException extends RuntimeException {

    private final int httpStatusCode;

    public CustomApiException(String msg, int httpStatusCode) {
        super(msg);
        this.httpStatusCode = httpStatusCode;
    }
}
