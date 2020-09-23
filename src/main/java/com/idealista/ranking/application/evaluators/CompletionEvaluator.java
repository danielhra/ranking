package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.application.Evaluator;
import com.idealista.ranking.application.evaluators.strategy.CompletionStrategyFactory;
import com.idealista.ranking.domain.Ad;
import lombok.Value;
import org.springframework.stereotype.Component;

@Component
@Value
public class CompletionEvaluator implements Evaluator {
    CompletionStrategyFactory strategyFactory;

    @Override
    public int evaluate(Ad ad) {
        return strategyFactory.chooseStrategyBy(ad.getTypology()).execute(ad);
    }


}
