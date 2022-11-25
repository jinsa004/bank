package shop.mtcoding.bank.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import shop.mtcoding.bank.config.auth.LoginUser;
import shop.mtcoding.bank.util.CustomResponseUtil;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final Logger log = LoggerFactory.getLogger(getClass());

    /***
     * /api/user/**, /api/account/**, /api/transaction/**, /api/admin/**
     * 위 주소일 때만 동작해야함
     */
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 1 헤더검증
        if (!isHeaderVerify(request, response)) {
            return;
        }
        // 2 토큰 파싱(Bearer 없애기)
        String token = request.getHeader(JwtProperties.HEADER_STRING)
                .replace(JwtProperties.TOKEN_PREFIX, "");

        try {
            // 3 토큰 검증
            LoginUser loginUser = JwtProcess.verify(token);
            // 4 임시 세션 생성
            Authentication authentication = new UsernamePasswordAuthenticationToken(loginUser,
                    loginUser.getPassword(), loginUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // 로그인을 해서 토큰을 지급받는 코드인데 이미 로그인이 되었기때문에 필요없어짐
            // UsernamePasswordAuthenticationToken authenticationToken = new
            // UsernamePasswordAuthenticationToken(
            // userPS.getUsername(),
            // userPS.getPassword());
            // 5 다음 필터로 이동
            chain.doFilter(request, response);
            return;
        } catch (Exception e) {
            CustomResponseUtil.fail(response, e.getMessage());
        }
    }

    private boolean isHeaderVerify(HttpServletRequest request, HttpServletResponse response) {
        String header = request.getHeader(JwtProperties.HEADER_STRING);
        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            CustomResponseUtil.fail(null, "토큰헤더가 없습니다.");
            return false;
        } else {
            return true;
        }
    }

}