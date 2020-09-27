package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.application.Evaluator;
import com.idealista.ranking.application.evaluators.strategy.CompletionStrategyFactory;
import com.idealista.ranking.domain.Ad;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class CompletionScoreEvaluator implements Evaluator {
    CompletionStrategyFactory strategyFactory;

    @Override
    public Mono<Integer> evaluate(Ad ad) {
        return strategyFactory.chooseStrategyBy(ad.getTypology()).execute(ad);
    }


}
