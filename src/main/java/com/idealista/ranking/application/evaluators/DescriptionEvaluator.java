package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.application.Evaluator;
import com.idealista.ranking.domain.Ad;

import java.util.List;
import java.util.function.ToIntFunction;
import java.util.regex.Pattern;

import static com.idealista.ranking.domain.Ad.Typology.CHALET;
import static com.idealista.ranking.domain.Ad.Typology.FLAT;

public class DescriptionEvaluator implements Evaluator {

    public static final int SCORE_FOR_HAVING_A_DESCRIPTION = 5;
    public static final int SCORE_FOR_FLAT_DESCRIPTION_BETWEEN_29_AND_49_WORDS = 10;
    public static final int SCORE_FOR_CHALET_DESCRIPTION_WITH_MORE_THAN_50_WORDS = 20;
    public static final int SCORE_FOR_FLAT_DESCRIPTION_WITH_MORE_THAN_50_WORDS = 30;
    public static final int SCORE_FOR_MATCHING_WORD = 5;

    private final List<Pattern> scoringWords = List.of(
            Pattern.compile("Luminoso"),
            Pattern.compile("Nuevo"),
            Pattern.compile("Céntrico"),
            Pattern.compile("Reformado"),
            Pattern.compile("Ático"));

    public DescriptionEvaluator() {
    }

    @Override
    public int evaluate(Ad ad) {
        if (thereIsNoDescription(ad)) {
            return 0;
        }
        return SCORE_FOR_HAVING_A_DESCRIPTION
                + calculateScoreByNumberOfWordsAndTypology(ad)
                + calculateScoringWords(ad.getDescription());
    }

    private int calculateScoreByNumberOfWordsAndTypology(Ad ad) {
        if (FLAT == ad.getTypology()) {
            return calculateFlatScoreByNumberOfWords(ad.getDescription());
        }
        if (CHALET == ad.getTypology()) {
            return calculateChaletScoreByNumberOfWords(ad.getDescription());
        }
        return 0;
    }

    private int calculateScoringWords(String description) {
        return scoringWords.stream()
                .mapToInt(calculateMatchingWordsInDescription(description))
                .sum();
    }

    private ToIntFunction<Pattern> calculateMatchingWordsInDescription(String description) {
        return scoringWord -> (int) (scoringWord.matcher(description).results().count() * SCORE_FOR_MATCHING_WORD);
    }


    private int calculateChaletScoreByNumberOfWords(String description) {
        return descriptionHasMoreThan50Words(description) ? SCORE_FOR_CHALET_DESCRIPTION_WITH_MORE_THAN_50_WORDS : 0;
    }

    private boolean thereIsNoDescription(Ad ad) {
        return ad.getDescription() == null || ad.getDescription().isEmpty();
    }

    private int calculateFlatScoreByNumberOfWords(String description) {
        if (descriptionHasBetween29And49Words(description)) {
            return SCORE_FOR_FLAT_DESCRIPTION_BETWEEN_29_AND_49_WORDS;
        } else if (descriptionHasMoreThan50Words(description)) {
            return SCORE_FOR_FLAT_DESCRIPTION_WITH_MORE_THAN_50_WORDS;
        }
        return 0;
    }

    private boolean descriptionHasMoreThan50Words(String description) {
        return description.length() >= 50;
    }

    private boolean descriptionHasBetween29And49Words(String description) {
        return description.length() >= 29 && description.length() <= 49;
    }

}
