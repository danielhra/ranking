package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.application.Evaluator;
import com.idealista.ranking.application.evaluators.descriptionChain.ChaletDescriptionEvaluatorChain;
import com.idealista.ranking.application.evaluators.descriptionChain.FlatDescriptionEvaluatorChain;
import com.idealista.ranking.application.evaluators.descriptionChain.ScoringWordsDescriptionEvaluatorChain;
import com.idealista.ranking.domain.Ad;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

@Component
public class DescriptionEvaluator implements Evaluator {

    public static final int SCORE_FOR_HAVING_A_DESCRIPTION = 5;

    private final List<Pattern> scoringWords = List.of(
            Pattern.compile("Luminoso"),
            Pattern.compile("Nuevo"),
            Pattern.compile("Céntrico"),
            Pattern.compile("Reformado"),
            Pattern.compile("Ático"));

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
