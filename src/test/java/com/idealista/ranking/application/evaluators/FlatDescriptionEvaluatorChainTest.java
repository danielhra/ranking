package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.application.evaluators.descriptionChain.DescriptionEvaluatorChain;
import com.idealista.ranking.application.evaluators.descriptionChain.FlatDescriptionEvaluatorChain;
import com.idealista.ranking.domain.Ad;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class FlatDescriptionEvaluatorChainTest {

    DescriptionEvaluatorChain handler;

    @BeforeEach
    void setUp() {
        handler = new FlatDescriptionEvaluatorChain();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "this description contains exa",
            "this description contains more than 29 words",
            "this description contains exactly     49    words",

    })
    void shouldAdd10PointsIfItsAFlatAndItHasBetween29And49WordsInclusive(String description) {
        final int actual = handler.evaluate(
                Ad.builder()
                        .typology(Ad.Typology.FLAT)
                        .description(description)
                        .build()
        );
        Assertions.assertThat(actual).isEqualTo(10);
    }

    @Test
    void shouldAdd30PointsIfItsAFlatAndContainsMoreThanOrEqual50Words() {

        final int actual = handler.evaluate(
                Ad.builder()
                        .typology(Ad.Typology.FLAT)
                        .description("this is a very long description that provides no information")
                        .build()
        );
        Assertions.assertThat(actual).isEqualTo(30);
    }

    @Test
    void shouldNotAddMorePointsIfItsNotAFlat() {

        final int actual = handler.evaluate(
                Ad.builder()
                        .typology(Ad.Typology.GARAGE)
                        .description("this is a very long description that provides no information")
                        .build()
        );
        Assertions.assertThat(actual).isZero();
    }
}
