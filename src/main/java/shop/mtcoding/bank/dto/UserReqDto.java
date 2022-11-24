package shop.mtcoding.bank.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import shop.mtcoding.bank.config.enums.UserEnum;
import shop.mtcoding.bank.domain.user.User;

public class UserReqDto {
    @Getter
    @Setter
    public static class JoinReqDto {
        @Size(min = 2, max = 20) // @Size 문자열 @Digits 숫자열
        @NotBlank(message = "유저네임은 필수입니다.")
        private String username;
        @Pattern(regexp = "^[가-힣]{4,20}", message = "비밀번호는 영문, 숫자, 특수문자 최소 4자리에서 최대 20자리까지입니다.")
        private String password;
        private String email;

        public User toEntity() {
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role(UserEnum.CUSTOMER)
                    .build();

        }
    }

}
