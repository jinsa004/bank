package shop.mtcoding.bank.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    // LoginUserService에 필요해서 만들어줌
    @Query("select u from User u where username = :username")
    Optional<User> findByUsername(@Param("username") String username); // findBy까지 적고 필드값을 적으면 뒤에껄로 자동쿼리를 만들어줌
}
