package com.idealista.ranking.adapters.in;

import com.idealista.ranking.application.ports.in.CalculateScoreUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class calculateScoreController {

    private final CalculateScoreUseCase calculateScoreUseCase;

    public calculateScoreController(CalculateScoreUseCase calculateScoreUseCase) {
        this.calculateScoreUseCase = calculateScoreUseCase;
    }

    @PostMapping("/v1/score/calculate")
    public void calculateScore(){
        calculateScoreUseCase.calculateScore();
    }



}
