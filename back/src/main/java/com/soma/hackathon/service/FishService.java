package com.soma.hackathon.service;

import com.soma.hackathon.entity.Fish;
import com.soma.hackathon.entity.FishDetails;
import com.soma.hackathon.entity.FishMaps;
import com.soma.hackathon.repository.FishDetailsRepository;
import com.soma.hackathon.repository.FishMapsRepository;
import com.soma.hackathon.repository.FishRepository;
import com.soma.hackathon.response.FishDetailsResponse;
import com.soma.hackathon.response.FishMapsResponse;
import com.soma.hackathon.response.FishResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class FishService {

    private final FishRepository fishRepository;
    private final FishDetailsRepository fishDetailsRepository;
    private final FishMapsRepository fishMapsRepository;
    private final ModelMapper modelMapper;

    public List<FishResponse> getFishes(String keyword) {

        List<Fish> fishes = null;
        if ("".equals(keyword) || keyword == null) {
            fishes = fishRepository.findAll();
        } else {
            fishes = fishRepository.findByFishNameIsContaining(keyword);
        }

        List<FishResponse> response = new ArrayList<>();
        fishes.forEach(f -> {
            response.add(modelMapper.map(f, FishResponse.class));
        });

        return response;
    }

    public FishDetailsResponse getFishDetails(String fishCode) {
        Fish fish = fishRepository.findOneByFishCode(fishCode);
        List<FishDetails> details = fishDetailsRepository.findByFishId(fish.getId());

        FishDetailsResponse response = FishDetailsResponse.builder()
                .fishCode(fish.getFishCode())
                .fishName(fish.getFishName())
                .imageURL(fish.getImageURL())
                .description(fish.getDescription())
                .prices(new ArrayList<>())
                .build();

        // 평균 구하기
        response.setPrices(getAvg(details));

        return response;
    }

    private List<FishDetailsResponse.AvgPrice> getAvg(List<FishDetails> details) {
        List<FishDetailsResponse.AvgPrice> price = new ArrayList<>();
        int[] monthCount = new int[5];
        int[] monthSumPrice = new int[5];

        details.forEach(d -> {
            int month = Integer.parseInt(d.getAuDate().substring(5));
            monthCount[month - 1] += 1;
            monthSumPrice[month - 1] += d.getAvgCost();
        });

        for (int i = 4; i >= 0; i--) {
            if (monthCount[i] == 0) {
                price.add(FishDetailsResponse.AvgPrice.builder()
                        .yearMonth("20210" + String.valueOf(i + 1))
                        .price(0)
                        .build());
            } else {
                price.add(FishDetailsResponse.AvgPrice.builder()
                        .yearMonth("20210" + String.valueOf(i + 1))
                        .price(Math.round(monthSumPrice[i] / monthCount[i]))
                        .build());
            }
        }

        return price;
    }

    public List<FishMapsResponse> getFishMaps() {
        List<FishMaps> maps = fishMapsRepository.findAll();
        List<FishMapsResponse> response = new ArrayList<>();

        maps.forEach(m -> {
            response.add(FishMapsResponse.builder()
                    .title(m.getTitle())
                    .id(m.getMarketId())
                    .x(m.getX())
                    .y(m.getY())
                    .build());
        });

        return response;
    }
}
