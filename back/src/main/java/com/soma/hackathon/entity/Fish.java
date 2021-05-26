package com.soma.hackathon.entity;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Fish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fish_id")
    private Long id;

    @OneToMany(mappedBy = "fish")
    private List<FishDetails> fishDetails;

    @Column(name = "fish_code")
    private String fishCode;

    @Column(name = "fish_name")
    private String fishName;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageURL;
}
