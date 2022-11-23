package shop.mtcoding.bank.domain.transaction;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.bank.config.enums.TransactionEnum;
import shop.mtcoding.bank.domain.AudingTime;
import shop.mtcoding.bank.domain.account.Account;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "transaction")
@Entity
public class Transaction extends AudingTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)) // 포린키 제약조건을 없애버리는 어노테이션
    @ManyToOne(fetch = FetchType.LAZY)
    private Account wirhdrawAccount; // 출금계좌

    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)) // 포린키 제약조건을 없애버리는 어노테이션
    @ManyToOne(fetch = FetchType.LAZY)
    private Account depositAccount; // 입금계좌

    @Column(nullable = false)
    private Long amount; // 금액

    private Long withdrawAccountBalance; // 거래 후 잔액
    private Long depositAccountBalance; // 거래 후 잔액 // 어떤 구분을 실행하냐에 따라 NULL일 수 있기때문에 Nullable처리하지않음

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionEnum gubun;// 입금(ATM으로 부터), 출금(ATM으로), 이체(다른계좌로)

    @Builder
    public Transaction(Long id, Account wirhdrawAccount, Account depositAccount, Long amount,
            Long withdrawAccountBalance, Long depositAccountBalance, TransactionEnum gubun) {
        this.id = id;
        this.wirhdrawAccount = wirhdrawAccount;
        this.depositAccount = depositAccount;
        this.amount = amount;
        this.withdrawAccountBalance = withdrawAccountBalance;
        this.depositAccountBalance = depositAccountBalance;
        this.gubun = gubun;
    }

}
