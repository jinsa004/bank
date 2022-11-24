package shop.mtcoding.bank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import shop.mtcoding.bank.config.enums.UserEnum;
import shop.mtcoding.bank.handler.CustomLoginHandler;

// SecurityFilterChain
@Configuration // IoC 컨테이너에 리턴 값을 재등록해주기 위해서 사용 // Configuration에는 Autowired로 DI해라.
public class SecurityConfig {

    @Autowired
    private CustomLoginHandler customLoginHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 암호를 sha256썼었는데 이걸 걸어주면 안써도 됨
    }

    @Bean // IoC 컨테이너에 리턴 값을 재등록해주기 위해서 사용
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.csrf().disable(); // csrf란 내가 지정해준 코드를 바탕으로 브라우저에서 요청이 올 때 코드를 들고오게 설정해서 포스트맨이나 장난질치는걸 막으려고 하는 것

        http.authorizeHttpRequests()
                .antMatchers("/api/transaction/**").authenticated()
                .antMatchers("/api/user/**").authenticated()
                .antMatchers("/api/account/**").authenticated()
                .antMatchers("/api/admin/**").hasRole("ROLE_" + UserEnum.ADMIN)
                .anyRequest().permitAll()
                .and()
                .formLogin() // 폼 로그인의 디폴트는 x-www-form-urlencoded (POST) (스프링의 기본 전략)
                .usernameParameter("username") // username password 키값도 디폴트 값
                .passwordParameter("password")
                .loginProcessingUrl("/api/login")
                .successHandler(customLoginHandler)
                .failureHandler(customLoginHandler);

        return http.build();
    }
}
