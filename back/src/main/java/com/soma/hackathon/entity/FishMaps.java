package com.soma.hackathon.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class FishMaps {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fish_maps_id")
    private Long id;

    private String title;
    private String marketId;
    private String x;
    private String y;
}
