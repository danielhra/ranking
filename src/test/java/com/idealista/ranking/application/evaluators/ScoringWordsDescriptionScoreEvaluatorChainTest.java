package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.application.evaluators.chain.DescriptionEvaluator;
import com.idealista.ranking.application.evaluators.chain.MatchingWordsScoreEvaluator;
import com.idealista.ranking.domain.Ad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class ScoringWordsDescriptionScoreEvaluatorChainTest {

    DescriptionEvaluator handler;

    @BeforeEach
    void setUp() {
        handler = new MatchingWordsScoreEvaluator();
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "es un piso muy Luminoso",
            "entre sus características es Cálido, Nuevo y Elegante",
            "Céntrico",
            "Reformado",
            "Ático"
    })
    void shouldAdd5PointsForEachScoringWord(String description) {
        StepVerifier.create(
                handler.evaluate(
                        Mono.just(Ad.builder()
                                .typology(Ad.Typology.GARAGE)
                                .description(description)
                                .build()
                ))).expectNext(5).verifyComplete();
    }

    @Test
    void shouldAddPointsForEachRepeatedWord() {
        StepVerifier.create(
                handler.evaluate(
                        Mono.just(Ad.builder()
                                .typology(Ad.Typology.GARAGE)
                                .description("Luminoso, Luminoso, Luminoso, Luminoso")
                                .build()
                ))).expectNext(20).verifyComplete();
    }

}
