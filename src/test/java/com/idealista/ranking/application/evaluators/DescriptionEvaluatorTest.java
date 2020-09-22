package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.domain.Ad;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(strings = {
            "this description contains exa",
            "this description contains more than 29 words",
            "this description contains exactly     49    words",

    })
    void shouldAdd15PointsIfItsAFlatAndItHasBetween29And49WordsInclusive(String description) {
        final int actual = sut.evaluate(
                Ad.builder()
                        .typology(Ad.Typology.FLAT)
                        .description(description)
                        .build()
        );
        Assertions.assertThat(actual).isEqualTo(15);
    }

    @Test
    void shouldAdd35PointsIfContainsMoreThanOrEqual50Words() {

        final int actual = sut.evaluate(
                Ad.builder()
                        .typology(Ad.Typology.FLAT)
                        .description("this is a very long description that provides no information")
                        .build()
        );
        Assertions.assertThat(actual).isEqualTo(35);
    }

    @Test
    void shouldNotAddMorePointsIfItsNotAFlatOrChalet() {

        final int actual = sut.evaluate(
                Ad.builder()
                        .typology(Ad.Typology.GARAGE)
                        .description("this is a very long description that provides no information")
                        .build()
        );
        Assertions.assertThat(actual).isEqualTo(5);
    }

    @Test
    void shouldAdd55PointsIfItsAChaletAndItHasMoreThanOrEqual50words() {
        final int actual = sut.evaluate(
                Ad.builder()
                        .typology(Ad.Typology.CHALET)
                        .description("this is a very long description that provides no information")
                        .build()
        );
        Assertions.assertThat(actual).isEqualTo(25);
    }

    @Test
    void shouldNotAddExtraPointsIfChaletHasLessThan50Words() {
        final int actual = sut.evaluate(
                Ad.builder()
                        .typology(Ad.Typology.CHALET)
                        .description("this description not long enough")
                        .build()
        );
        Assertions.assertThat(actual).isEqualTo(5);
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
        final int actual = sut.evaluate(
                Ad.builder()
                        .typology(Ad.Typology.GARAGE)
                        .description(description)
                        .build()
        );
        Assertions.assertThat(actual).isEqualTo(10);
    }

    @Test
    void shouldAddPointsForEachRepeatedWord() {
        final int actual = sut.evaluate(
                Ad.builder()
                        .typology(Ad.Typology.GARAGE)
                        .description("Luminoso, Luminoso, Luminoso, Luminoso")
                        .build()
        );
        Assertions.assertThat(actual).isEqualTo(25);
    }
}
