package com.feedback.hafit.domain.goal.entity;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.keyword.entity.Keyword;
import com.feedback.hafit.domain.plan.entity.Plan;
import com.feedback.hafit.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "goals")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Goal extends BaseEntity {
    // 운동 목표 엔티티
    // 시작일은 BaseEntity의 createdAt을 사용

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long goal_id; // 목표 번호

    @Column(length = 300)
    private String goal_content; // 목표 내용

    private LocalDate goal_date; // 종료일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_goal_user"))
    private User user; // 회원 번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "keyword_id", foreignKey = @ForeignKey(name = "fk_goal_keyword"))
    private Keyword keyword; // 키워드 번호(운동 추천용으로 사용, 키워드와 연결)
}
