package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.domain.Ad;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.idealista.ranking.domain.Ad.Typology.CHALET;

class DescriptionEvaluatorTest {

    DescriptionEvaluator sut;

    @BeforeEach
    void setUp() {
        sut = new DescriptionEvaluator();
    }

    @Test
    void shouldAdd5PointsIfItHasDescription() {

        final int actual = sut.evaluate(
                Ad.builder()
                        .description("description")
                        .build()
        );
        Assertions.assertThat(actual).isEqualTo(5);
    }

    @Test
    void shouldAddPointsForEachDescriptionChain() {

        final int actual = sut.evaluate(
                Ad.builder()
                        .typology(CHALET)
                        .description("Nuevo ático céntrico recién reformado. No deje pasar la oportunidad y adquiera este ático de lujo")
                        .build()
        );
        Assertions.assertThat(actual).isEqualTo(30);

    }
}
