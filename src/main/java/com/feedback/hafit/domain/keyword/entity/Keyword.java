package com.feedback.hafit.domain.keyword.entity;

import com.feedback.hafit.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "keywords")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Keyword extends BaseEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    public Long keywordId;

    @Column(length = 300)
    private String keyword_content;


}
