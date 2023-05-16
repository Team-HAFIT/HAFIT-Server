package com.feedback.hafit.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "goal")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Goal {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long goal_id;

    @Column(length = 300)
    private String goal_content; //아이디

    @Column
    private LocalDate goal_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_goal_user"))
    private User user;

}
