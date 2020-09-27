package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.application.Evaluator;
import com.idealista.ranking.application.evaluators.chain.DescriptionEvaluatorFactory;
import com.idealista.ranking.domain.Ad;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class DescriptionScoreEvaluator implements Evaluator {

    public static final int SCORE_FOR_HAVING_A_DESCRIPTION = 5;
    public static final int NO_SCORE = 0;

    private final DescriptionEvaluatorFactory factory;

    @Override
    public Mono<Integer> evaluate(Ad ad) {
        if (thereIsNoDescription(ad)) {
            return Mono.just(NO_SCORE);
        }
        return factory.get()
                .flatMap(scorers -> scorers.evaluate(Mono.just(ad)))
                .startWith(SCORE_FOR_HAVING_A_DESCRIPTION)
                .reduce(Integer::sum);
    }

    private boolean thereIsNoDescription(Ad ad) {
        return ad.getDescription() == null || ad.getDescription().isEmpty();
    }


}
