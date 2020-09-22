package com.idealista.ranking.application;

import com.idealista.ranking.application.evaluators.CompletionEvaluator;
import com.idealista.ranking.application.evaluators.DescriptionEvaluator;
import com.idealista.ranking.application.evaluators.PictureEvaluator;
import com.idealista.ranking.application.ports.in.CalculateScoreUseCase;
import com.idealista.ranking.application.ports.out.CalculateScoreRepository;
import com.idealista.ranking.domain.Ad;
import lombok.Value;
import org.springframework.stereotype.Service;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Service
@Value
public class CalculateScoreService implements CalculateScoreUseCase {

    public static final int MIN_THRESHOLD = 0;
    public static final int MAX_THRESHOLD = 100;
    CalculateScoreRepository repository;
    PictureEvaluator pictureEvaluator;
    DescriptionEvaluator descriptionEvaluator;
    CompletionEvaluator completionEvaluator;

    @Override
    public void calculateScore() {

        repository.getAdsWithoutScore()
                .map(this::handleScore)
                .doOnNext(repository::save)
                .subscribe();
    }

    private int checkLimits(int score) {
        return min(MAX_THRESHOLD,
                max(MIN_THRESHOLD, score));
    }

    private Ad handleScore(Ad ad) {
        int score = pictureEvaluator.evaluate(ad)
                + descriptionEvaluator.evaluate(ad)
                + completionEvaluator.evaluate(ad);

        return ad.withScore(checkLimits(score));

    }
}
