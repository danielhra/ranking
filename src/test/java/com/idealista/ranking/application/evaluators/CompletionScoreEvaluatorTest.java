package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.application.evaluators.strategy.CompletionStrategyFactory;
import com.idealista.ranking.motherobjects.AdMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static com.idealista.ranking.domain.Ad.Typology.*;

class CompletionScoreEvaluatorTest {


    CompletionScoreEvaluator completionScoreEvaluator;

    @BeforeEach
    void setUp() {
        completionScoreEvaluator = new CompletionScoreEvaluator(new CompletionStrategyFactory());
    }

    @Test
    void shouldAdd40PointsIfFlatIsComplete() {
        StepVerifier.create(
                completionScoreEvaluator.evaluate(
                        AdMother.getPrebuiltAd()
                                .typology(FLAT)
                                .build()
                ))
                .expectNext(40)
                .verifyComplete();

    }

    @Test
    void shouldNotAddPointsIfFlatIsIncomplete() {
        StepVerifier.create(
                completionScoreEvaluator.evaluate(
                        AdMother.getPrebuiltAd()
                                .typology(FLAT)
                                .description(null)
                                .build()
                ))
                .expectNext(0)
                .verifyComplete();

    }

    @Test
    void shouldAdd40PointsIfChaletIsComplete() {
        StepVerifier.create(
                completionScoreEvaluator.evaluate(
                        AdMother.getPrebuiltAd()
                                .typology(CHALET)
                                .build()
                ))
                .expectNext(40)
                .verifyComplete();

    }

    @Test
    void shouldNotAddPointsIfChaletIsIncomplete() {
        StepVerifier.create(
                completionScoreEvaluator.evaluate(
                        AdMother.getPrebuiltAd()
                                .typology(CHALET)
                                .gardenSize(null)
                                .build()
                ))
                .expectNext(0)
                .verifyComplete();

    }

    @Test
    void shouldAddPointsIfGarageIsCompletedWithoutDescription() {
        StepVerifier.create(
                completionScoreEvaluator.evaluate(
                        AdMother.getPrebuiltAd()
                                .typology(GARAGE)
                                .description(null)
                                .build()
                ))
                .expectNext(40)
                .verifyComplete();


    }

    @Test
    void shouldNotAddPointsIfAdDoesntHavePictures() {
        StepVerifier.create(
                completionScoreEvaluator.evaluate(
                        AdMother.getPrebuiltAd()
                                .typology(FLAT)
                                .pictures(null)
                                .build()
                ))
                .expectNext(0)
                .verifyComplete();


    }
}
