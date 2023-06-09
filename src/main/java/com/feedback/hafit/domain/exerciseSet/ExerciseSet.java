package com.feedback.hafit.domain.exerciseSet;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.plan.entity.Plan;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "sets")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExerciseSet extends BaseEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long set_id;

    @Column
    private int group_id;

    @Column
    private int weight;

    @Column
    private int score;

    @Column(name="targetcount")
    private int targetCount;

    @Column(name="realcount")
    private int realCount;

    @Column(name="targetset")
    private int targetSet;

    @Column(name="realset")
    private int realSet;

    @Column(name="starttime")
    private int startTime;

    @Column(name="limittime")
    private int limitTime;

    @Column(name="realtime")
    private int realTime;

    @Column
    private LocalDateTime date;

    @Column
    private char perform_status;

    // Plan 참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id", foreignKey = @ForeignKey(name = "fk_set_plan"))
    private Plan plan;

}
