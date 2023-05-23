package com.feedback.hafit.domain;

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

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long goal_id;

    @Column(length = 300)
    private String goal_content; //아이디

    private LocalDate goal_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_goal_user"))
    private User user;

    // Plan 참조
    @OneToMany(mappedBy = "goal", fetch = FetchType.LAZY)
    private List<Plan> plans = new ArrayList<>();

}
