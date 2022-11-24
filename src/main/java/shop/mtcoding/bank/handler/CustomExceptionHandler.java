package shop.mtcoding.bank.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import shop.mtcoding.bank.config.exception.CustomApiException;
import shop.mtcoding.bank.dto.ResponseDto;

// 필터 쪽에서 터지는 오류는 컨트롤러, 어드바이스가 낚아채지 못함(SF 시큐리티필터가 낚아채서 오지를 않음)
@RestControllerAdvice // 익셉션을 처리하는 컨트롤러
public class CustomExceptionHandler {

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomApiException e) {
        System.out.println("에러의 제어권을 잡음");
        // ResponseDto에 들어가는 e.getHttpStatusCode는 프론트개발자 보라고, 뒤에 status는 브라우저가 보라고 넣어줌
        return new ResponseEntity<>(new ResponseDto<>(e.getMessage(), null), e.getHttpStatus());
    }
}
