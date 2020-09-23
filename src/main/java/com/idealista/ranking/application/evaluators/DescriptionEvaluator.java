package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.application.Evaluator;
import com.idealista.ranking.application.evaluators.chain.ChaletDescriptionEvaluatorChain;
import com.idealista.ranking.application.evaluators.chain.FlatDescriptionEvaluatorChain;
import com.idealista.ranking.application.evaluators.chain.ScoringWordsDescriptionEvaluatorChain;
import com.idealista.ranking.domain.Ad;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class DescriptionEvaluator implements Evaluator {

    public static final int SCORE_FOR_HAVING_A_DESCRIPTION = 5;

    @Override
    public int evaluate(Ad ad) {
        if (thereIsNoDescription(ad)) {
            return 0;
        }
        return createEvaluationChain(ad.withScore(SCORE_FOR_HAVING_A_DESCRIPTION));
    }

    private boolean thereIsNoDescription(Ad ad) {
        return ad.getDescription() == null || ad.getDescription().isEmpty();
    }

    private int createEvaluationChain(Ad ad) {
        return new FlatDescriptionEvaluatorChain()
                .linkNext(new ChaletDescriptionEvaluatorChain())
                .linkNext(new ScoringWordsDescriptionEvaluatorChain())
                .evaluate(ad);
    }


}
