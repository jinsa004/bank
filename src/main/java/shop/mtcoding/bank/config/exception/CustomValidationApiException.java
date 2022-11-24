package shop.mtcoding.bank.config.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Getter;

//유효성오류에 사용
@Getter
public class CustomValidationApiException extends RuntimeException {

    private final HttpStatus httpStatus;
    private Map<String, String> errorMap;

    // msg = "유효성 검사 실패"
    public CustomValidationApiException(Map<String, String> errorMap) {
        super("유효성 검사 실패");
        this.errorMap = errorMap;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }
}
