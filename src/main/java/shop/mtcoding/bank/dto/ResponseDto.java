package shop.mtcoding.bank.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//http code = 200(Post를 제외한 모든 요청이 정상적일 때, 로그인도 200), 201(POST)
@Getter
@RequiredArgsConstructor
public class ResponseDto<T> {
    // 변수들을 변경할 일이 없기 때문에 final을 붙여주고 final이기 때문에 @Allargment말고 @Require를 붙여줌
    private final String msg;
    private final T data;
}
