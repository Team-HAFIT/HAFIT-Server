package com.feedback.hafit.domain;

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
@Table(name = "hashtags")
@NoArgsConstructor
@AllArgsConstructor
public class Hashtag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hashtag_id; //sequence, auto_increment

    @Column(length = 50)
    private String hashtag;

    @OneToMany(mappedBy = "post_hashtag_id")
    private List<PostHashtag> postHashtags = new ArrayList<>();

}
