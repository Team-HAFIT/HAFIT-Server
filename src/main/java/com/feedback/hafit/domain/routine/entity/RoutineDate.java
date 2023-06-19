package com.feedback.hafit.domain.routine.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "routine_date")
@AllArgsConstructor
@NoArgsConstructor
public class RoutineDate { // 루틴_일자 연결

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routineDateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id", foreignKey = @ForeignKey(name = "fk_routine_date"))
    private Routine routine;

    @Column
    private LocalDate days;

    @Column
    private String perform;

}
