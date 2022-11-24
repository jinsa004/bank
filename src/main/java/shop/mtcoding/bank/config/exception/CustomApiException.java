package shop.mtcoding.bank.config.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

// 일반적인 에러
@Getter
public class CustomApiException extends RuntimeException {

    private final HttpStatus httpStatus;

    public CustomApiException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}
