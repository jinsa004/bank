package shop.mtcoding.bank.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass // 자식이 AudingTime을 상속할껀데, 자식이 얘를 테이블에 칼럼 만들어라는 어노테이션
@EntityListeners(AuditingEntityListener.class)
public abstract class AudingTime { // 얘 자체만 쓰지못하게 추상화

  @LastModifiedDate // Insert, update시에 현재시간 들어감
  @Column(nullable = false)
  protected LocalDateTime updatedAt; // Entity로 넘길시 LocalDateTime을 스프링이 파싱하지 못하기 때문에 Dto로 넘겨주자!

  @CreatedDate // Insert 시 현재시간 들어감
  @Column(nullable = false)
  protected LocalDateTime createdAt;
}
