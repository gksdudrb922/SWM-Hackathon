package com.soma.hackathon.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FishDetailsResponse {

    private String fishCode;
    private String fishName;
    private List<AvgPrice> prices;
    private String imageURL;
    private String description;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AvgPrice {

        private String yearMonth;
        private int price;
    }
}
