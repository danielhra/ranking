package com.idealista.ranking.application;

import com.idealista.ranking.application.evaluators.EvaluatorFactory;
import com.idealista.ranking.application.ports.in.CalculateScoreUseCase;
import com.idealista.ranking.application.ports.out.CalculateScoreRepository;
import com.idealista.ranking.domain.Ad;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Service
@AllArgsConstructor
public class CalculateScoreService implements CalculateScoreUseCase {

    public static final int MIN_THRESHOLD = 0;
    public static final int MAX_THRESHOLD = 100;
    CalculateScoreRepository repository;

    EvaluatorFactory evaluatorFactory;


    @Override
    public void calculateScore() {

        repository.getAdsWithoutScore()
                .flatMap(this::handleScore)
                .doOnNext(repository::save)
                .subscribe();
    }

    private int checkLimits(int score) {
        return min(MAX_THRESHOLD,
                max(MIN_THRESHOLD, score));
    }

    private Mono<Ad> handleScore(Ad ad) {

        return evaluatorFactory.getEvaluators()
                .flatMap(evaluator -> evaluator.evaluate(ad))
                .reduce(Integer::sum)
                .map(score -> ad.withScore(checkLimits(score)));
    }
}
