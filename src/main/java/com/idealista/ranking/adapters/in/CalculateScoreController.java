package com.idealista.ranking.adapters.in;

import com.idealista.ranking.application.ports.in.CalculateScoreUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CalculateScoreController {

    private final CalculateScoreUseCase calculateScoreUseCase;


    @PostMapping("/v1/score/calculate")
    public void calculateScore(){
        calculateScoreUseCase.calculateScore();
    }



}
