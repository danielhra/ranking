package com.idealista.ranking.application.evaluators.descriptionChain;

import com.idealista.ranking.application.evaluators.descriptionChain.DescriptionEvaluatorChain;
import com.idealista.ranking.domain.Ad;

import java.util.List;
import java.util.function.ToIntFunction;
import java.util.regex.Pattern;

public class ScoringWordsDescriptionEvaluatorChain extends DescriptionEvaluatorChain {

    private static final int SCORE_FOR_MATCHING_WORD = 5;

    private final List<Pattern> scoringWords = List.of(
            Pattern.compile("Luminoso"),
            Pattern.compile("Nuevo"),
            Pattern.compile("Céntrico"),
            Pattern.compile("Reformado"),
            Pattern.compile("Ático"));

    @Override
    public int evaluate(Ad ad) {
        return super.evaluate(ad.withScore(calculateScoringWords(ad.getDescription())));
    }

    private int calculateScoringWords(String description) {
        return scoringWords.stream()
                .mapToInt(calculateMatchingWordsInDescription(description))
                .sum();
    }


    private ToIntFunction<Pattern> calculateMatchingWordsInDescription(String description) {
        return scoringWord -> (int) (scoringWord.matcher(description).results().count() * SCORE_FOR_MATCHING_WORD);
    }


}
