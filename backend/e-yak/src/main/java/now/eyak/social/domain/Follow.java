package now.eyak.social.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import now.eyak.member.domain.Member;
import now.eyak.social.Scope;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * follwer가 follo
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Member follower;
    @ManyToOne
    private Member followee;
    private String customName;
    @Enumerated(EnumType.STRING)
    private Scope followerScope;
    @Enumerated(EnumType.STRING)
    private Scope followeeScope;
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();
    @UpdateTimestamp
    private LocalDateTime updatedAt = LocalDateTime.now();
}