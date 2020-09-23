package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.application.Evaluator;
import com.idealista.ranking.domain.Ad;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CompletionEvaluator implements Evaluator {
    @Override
    public int evaluate(Ad ad) {
        return 0;
    }
}
