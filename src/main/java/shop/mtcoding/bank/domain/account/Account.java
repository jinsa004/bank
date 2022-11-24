package shop.mtcoding.bank.domain.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.bank.domain.AudingTime;
import shop.mtcoding.bank.domain.user.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "account")
@Entity
public class Account extends AudingTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private Long number;
    @Column(nullable = false, length = 50)
    private String ownerName; // 계좌주 실명
    @Column(nullable = false, length = 4)
    private String password;
    @Column(nullable = false)
    private Long balance; // 잔액
    // 커멜케이스는 DB에 언더스코어로 생성됨. => yml에서 전략을 걸어줘야하는데 우리는 언더스코어로 슬거임~!
    @Column(nullable = false)
    private Boolean isActive; // 계좌 활성화 여부

    @ManyToOne(fetch = FetchType.LAZY) // 개발자가 제어권을 가지기 위해서 LAZY 전략을 사용하여 필요한 값을 요청/응답하는 것이 좋다.
    private User user;

    @Builder
    public Account(Long id, Long number, String ownerName, String password, Long balance, Boolean isActive, User user) {
        this.id = id;
        this.number = number;
        this.ownerName = ownerName;
        this.password = password;
        this.balance = balance;
        this.isActive = isActive;
        this.user = user;
    }

}
