package com.feedback.hafit.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "exercises")
@NoArgsConstructor
@AllArgsConstructor
public class Exercise extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exec_id;

    @Column
    private String name;

    @Column
    private String calorie;

    @Column
    private int part;

    @Column
    private String exec_description;

    @Column
    private String exec_img;

    // Plan 참조
    @OneToMany(mappedBy = "exercise", fetch = FetchType.LAZY)
    private List<Plan> plans = new ArrayList<>();

}
