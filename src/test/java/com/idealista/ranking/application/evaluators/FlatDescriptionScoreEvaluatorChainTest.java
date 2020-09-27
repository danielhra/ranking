package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.application.evaluators.chain.DescriptionEvaluator;
import com.idealista.ranking.application.evaluators.chain.FlatDescriptionScoreEvaluator;
import com.idealista.ranking.domain.Ad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class FlatDescriptionScoreEvaluatorChainTest {

    DescriptionEvaluator handler;

    @BeforeEach
    void setUp() {
        handler = new FlatDescriptionScoreEvaluator();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "this description contains exa",
            "this description contains more than 29 words",
            "this description contains exactly     49    words",

    })
    void shouldAdd10PointsIfItsAFlatAndItHasBetween29And49WordsInclusive(String description) {
        StepVerifier.create(
         handler.evaluate(
                 Mono.just(Ad.builder()
                        .typology(Ad.Typology.FLAT)
                        .description(description)
                        .build()
        ))).expectNext(10).verifyComplete();
    }

    @Test
    void shouldAdd30PointsIfItsAFlatAndContainsMoreThanOrEqual50Words() {

        StepVerifier.create(
         handler.evaluate(
                Mono.just(Ad.builder()
                        .typology(Ad.Typology.FLAT)
                        .description("this is a very long description that provides no information")
                        .build()
        ))).expectNext(30).verifyComplete();
    }

    @Test
    void shouldNotAddMorePointsIfItsNotAFlat() {

        StepVerifier.create(
         handler.evaluate(
                Mono.just(Ad.builder()
                        .typology(Ad.Typology.GARAGE)
                        .description("this is a very long description that provides no information")
                        .build()
        ))).verifyComplete();
    }
}
