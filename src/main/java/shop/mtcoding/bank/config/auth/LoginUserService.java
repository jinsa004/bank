package shop.mtcoding.bank.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import shop.mtcoding.bank.config.exception.CustomApiException;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserRepository;

@Service
public class LoginUserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // 유저가 있는지 없는지만 확인해주면 패스워드는 filter가 자동으로 처리해줌
    // 우리 DB에 있는 USERNAME으로 로그인을 해라(커스터마이징)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 세션 값을 찾는 코드(하지만 얘는 시스템이 쓰게 놔두고 내가 편하게 쓰기위해 세션을 하나 더 만들어서 사용함)
        // LoginUser loginUser = (LoginUser)
        // SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomApiException("username을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST));
        return new LoginUser(user);
    }

}
