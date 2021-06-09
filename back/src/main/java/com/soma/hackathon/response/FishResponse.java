package com.soma.hackathon.response;

import com.soma.hackathon.entity.Fish;
import lombok.Data;

@Data
public class FishResponse {

    private String fishCode;
    private String fishName;
    private String description;
    private String imageURL;

    public FishResponse(Fish fish) {
        fishCode = fish.getFishCode();
        fishName = fish.getFishName();
        description = fish.getDescription();
        imageURL = fish.getImageURL();
    }
}
