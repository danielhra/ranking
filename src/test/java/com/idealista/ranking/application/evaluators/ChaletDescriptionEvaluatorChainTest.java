package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.application.evaluators.chain.ChaletDescriptionEvaluatorChain;
import com.idealista.ranking.application.evaluators.chain.DescriptionEvaluatorChain;
import com.idealista.ranking.domain.Ad;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChaletDescriptionEvaluatorChainTest {


    DescriptionEvaluatorChain handler;

    @BeforeEach
    void setUp() {
        handler = new ChaletDescriptionEvaluatorChain();
    }

    @Test
    void shouldAdd20PointsIfItsAChaletAndItHasMoreThanOrEqual50words() {
        final int actual = handler.evaluate(
                Ad.builder()
                        .typology(Ad.Typology.CHALET)
                        .description("this is a very long description that provides no information")
                        .build()
        );
        Assertions.assertThat(actual).isEqualTo(20);
    }

    @Test
    void shouldNotAddExtraPointsIfChaletHasLessThan50Words() {
        final int actual = handler.evaluate(
                Ad.builder()
                        .typology(Ad.Typology.CHALET)
                        .description("this description not long enough")
                        .build()
        );
        Assertions.assertThat(actual).isZero();
    }

    @Test
    void shouldNotAddMorePointsIfItsNotAChalet() {

        final int actual = handler.evaluate(
                Ad.builder()
                        .typology(Ad.Typology.GARAGE)
                        .description("this is a very long description that provides no information")
                        .build()
        );
        Assertions.assertThat(actual).isZero();
    }

}
