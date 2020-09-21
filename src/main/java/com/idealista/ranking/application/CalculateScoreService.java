package com.idealista.ranking.application;

import com.idealista.ranking.application.ports.in.CalculateScoreUseCase;
import com.idealista.ranking.application.ports.out.CalculateScoreRepository;
import lombok.Value;
import org.springframework.stereotype.Service;

@Service
@Value
public class CalculateScoreService implements CalculateScoreUseCase {

    CalculateScoreRepository repository;

    @Override
    public void calculateScore() {

        repository.getAdsWithoutScore()
                .map(ad -> {
                    Evaluator score =
                            new DescriptionEvaluator(
                                    new CompletionEvaluator(
                                            new PictureEvaluator(
                                                    new Score(0)))
                            );

                    return ad.withScore(score.evaluate());
                })
                .log()
                .subscribe();


    }
}
