package com.feedback.hafit.domain.exercise.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "body_parts")
@NoArgsConstructor
@AllArgsConstructor
public class BodyPart { // 자극 부위

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partId; // 부위 번호

    @Column(length = 100)
    private String part_name; // 부위 이름

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public void setPart_name(String part_name) {
        this.part_name = part_name;
    }
}