package com.soma.hackathon.controller;

import com.soma.hackathon.response.FishDetailsResponse;
import com.soma.hackathon.response.FishMapsResponse;
import com.soma.hackathon.response.FishResponse;
import com.soma.hackathon.service.FishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FishController {

    private final FishService fishService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "Healthy!";
    }

    @GetMapping("/fishes")
    public ResponseEntity<List<FishResponse>> getFishes(String keyword) {
        List<FishResponse> fishes = fishService.getFishes(keyword);
        return ResponseEntity.status(HttpStatus.OK).body(fishes);
    }

    @GetMapping("/fishes/{fishCode}")
    public ResponseEntity<FishDetailsResponse> getFishDetails(@PathVariable("fishCode") String fishCode) {
        FishDetailsResponse response = fishService.getFishDetails(fishCode);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/maps")
    public ResponseEntity<List<FishMapsResponse>> getMaps() {

        List<FishMapsResponse> responses = fishService.getFishMaps();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }
}
