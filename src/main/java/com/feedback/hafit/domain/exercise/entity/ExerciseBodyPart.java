package com.feedback.hafit.domain.exercise.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "ExerciseBodyParts")
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseBodyPart { // 운동_부위 연결

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exec_id", foreignKey = @ForeignKey(name = "fk_exec"))
    private Exercise exec_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id", foreignKey = @ForeignKey(name = "fk_part"))
    private BodyPart part_id;
}