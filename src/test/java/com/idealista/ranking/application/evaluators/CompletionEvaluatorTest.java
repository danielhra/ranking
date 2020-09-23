package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.application.evaluators.strategy.CompletionStrategyFactory;
import com.idealista.ranking.motherobjects.AdMother;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.idealista.ranking.domain.Ad.Typology.*;

class CompletionEvaluatorTest {

    CompletionEvaluator completionEvaluator;

    @BeforeEach
    void setUp() {
        completionEvaluator = new CompletionEvaluator(new CompletionStrategyFactory());
    }

    @Test
    void shouldAdd40PointsIfFlatIsComplete() {
        final int score = completionEvaluator.evaluate(
                AdMother.getPrebuiltAd()
                        .typology(FLAT)
                        .build()
        );

        Assertions.assertThat(score).isEqualTo(40);

    }

    @Test
    void shouldNotAddPointsIfFlatIsIncomplete() {
        final int score = completionEvaluator.evaluate(
                AdMother.getPrebuiltAd()
                        .typology(FLAT)
                        .description(null)
                        .build()
        );
        Assertions.assertThat(score).isZero();

    }

    @Test
    void shouldAdd40PointsIfChaletIsComplete() {
        final int score = completionEvaluator.evaluate(
                AdMother.getPrebuiltAd()
                        .typology(CHALET)
                        .build()
        );

        Assertions.assertThat(score).isEqualTo(40);

    }

    @Test
    void shouldNotAddPointsIfChaletIsIncomplete() {
        final int score = completionEvaluator.evaluate(
                AdMother.getPrebuiltAd()
                        .typology(CHALET)
                        .gardenSize(null)
                        .build()
        );

        Assertions.assertThat(score).isZero();

    }

    @Test
    void shouldAddPointsIfGarageIsCompletedWithoutDescription() {
        final int score = completionEvaluator.evaluate(
                AdMother.getPrebuiltAd()
                        .typology(GARAGE)
                        .description(null)
                        .build()
        );

        Assertions.assertThat(score).isEqualTo(40);

    }

    @Test
    void shouldNotAddPointsIfAdDoesntHavePictures() {
        final int score = completionEvaluator.evaluate(
                AdMother.getPrebuiltAd()
                        .typology(FLAT)
                        .pictures(null)
                        .build()
        );

        Assertions.assertThat(score).isZero();

    }
}
