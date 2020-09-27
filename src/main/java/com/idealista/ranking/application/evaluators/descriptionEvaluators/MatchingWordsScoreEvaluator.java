package com.idealista.ranking.application.evaluators.descriptionEvaluators;

import com.idealista.ranking.domain.Ad;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.regex.Pattern;

public class MatchingWordsScoreEvaluator implements DescriptionEvaluator {

    private static final int SCORE_FOR_MATCHING_WORD = 5;

    private final Flux<Pattern> scoringWords = Flux.just(
            Pattern.compile("Luminoso"),
            Pattern.compile("Nuevo"),
            Pattern.compile("Céntrico"),
            Pattern.compile("Reformado"),
            Pattern.compile("Ático"));

    @Override
    public Mono<Integer> evaluate(Mono<Ad> ad) {
        return ad.flatMap(a -> calculateScoringWords(a.getDescription()));
    }

    private Mono<Integer> calculateScoringWords(String description) {
        return scoringWords
                .map(scoringWord -> (int) scoringWord.matcher(description).results().count() * SCORE_FOR_MATCHING_WORD)
                .reduce(Integer::sum);
    }


}
