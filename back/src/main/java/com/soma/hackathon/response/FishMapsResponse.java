package com.soma.hackathon.response;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FishMapsResponse {

    private String title;
    private String id;
    private String x;
    private String y;
}
