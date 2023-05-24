package com.feedback.hafit.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "bodyparts")
@NoArgsConstructor
@AllArgsConstructor
public class BodyPart { // 자극 부위

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int part_id; // 부위 번호

    @Column(length = 100)
    private String name; // 부위 이름

}