package shop.mtcoding.bank.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.bank.config.enums.UserEnum;
import shop.mtcoding.bank.domain.AudingTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "users")
@Entity
public class User extends AudingTime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false, length = 20)
  private String username;
  @Column(nullable = false, length = 60)
  private String password;
  @Column(nullable = false, length = 50)
  private String email;
  @Enumerated(EnumType.STRING) // 데이터베이스에 들어갈 때 enum타입이 없기때문에 어노테이션으로 데이터타입을 지정해줌
  @Column(unique = true, nullable = false)
  private UserEnum role; // ADMIN, CUSTOMER 역할분배를 할 땐 String 말고 Enum으로 설계해라.

  @Builder
  public User(Long id, String username, String password, String email, UserEnum role) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.role = role;
  }

}
