package shop.mtcoding.bank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserRepository;
import shop.mtcoding.bank.dto.UserReqDto.JoinReqDto;
import shop.mtcoding.bank.dto.UserRespDto.JoinRespDto;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public JoinRespDto 회원가입(JoinReqDto joinReqDto) {
        // 시큐리티는 비밀번호를 해쉬하지 않으면 자동으로 터져버린다.
        // 1. 비밀번호 암호화
        String rawPassword = joinReqDto.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        joinReqDto.setPassword(encPassword);
        // 2. 회원가입
        User userPS = userRepository.save(joinReqDto.toEntity());
        log.debug("디버그 : 서비스 끝");
        // 3. DTO 응답
        return new JoinRespDto(userPS);
    }
}
