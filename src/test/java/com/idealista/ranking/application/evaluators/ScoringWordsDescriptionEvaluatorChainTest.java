package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.application.evaluators.descriptionChain.DescriptionEvaluatorChain;
import com.idealista.ranking.application.evaluators.descriptionChain.ScoringWordsDescriptionEvaluatorChain;
import com.idealista.ranking.domain.Ad;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ScoringWordsDescriptionEvaluatorChainTest {

    DescriptionEvaluatorChain handler;

    @BeforeEach
    void setUp() {
        handler = new ScoringWordsDescriptionEvaluatorChain();
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
        final int actual = handler.evaluate(
                Ad.builder()
                        .typology(Ad.Typology.GARAGE)
                        .description(description)
                        .build()
        );
        Assertions.assertThat(actual).isEqualTo(5);
    }

    @Test
    void shouldAddPointsForEachRepeatedWord() {
        final int actual = handler.evaluate(
                Ad.builder()
                        .typology(Ad.Typology.GARAGE)
                        .description("Luminoso, Luminoso, Luminoso, Luminoso")
                        .build()
        );
        Assertions.assertThat(actual).isEqualTo(20);
    }

}
