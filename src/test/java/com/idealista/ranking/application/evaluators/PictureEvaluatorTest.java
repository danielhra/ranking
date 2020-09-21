package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.domain.Picture;
import com.idealista.ranking.motherobjects.AdMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.idealista.ranking.domain.Picture.Quality.HD;
import static com.idealista.ranking.domain.Picture.Quality.SD;
import static org.assertj.core.api.Assertions.assertThat;

class PictureEvaluatorTest {

    PictureEvaluator sut;

    @BeforeEach
    void setUp() {
        sut = new PictureEvaluator();
    }

    @Test
    void shouldEvaluateNegativeIfItDoesntHavePictures() {

        int actual = sut.evaluate(AdMother
                .getPrebuiltAd()
                .pictures(null)
                .build());

        assertThat(actual).isEqualTo(-10);

        actual = sut.evaluate(
                AdMother.getPrebuiltAd()
                        .pictures(List.of())
                        .build());

        assertThat(actual).isEqualTo(-10);
    }

    @Test
    void shouldGive10PointsForNoHdPicture() {
        int actual = sut.evaluate(
                AdMother.getPrebuiltAd()
                        .pictures(List.of(
                                new Picture(1, "", SD),
                                new Picture(2, "", SD),
                                new Picture(3, "", SD),
                                new Picture(4, "", SD)
                        ))
                        .build());

        assertThat(actual).isEqualTo(40);
    }

    @Test
    void shouldGive20PointsForHdPicture() {
        int actual = sut.evaluate(
                AdMother.getPrebuiltAd()
                        .pictures(List.of(
                                new Picture(1, "", HD),
                                new Picture(2, "", HD),
                                new Picture(3, "", HD),
                                new Picture(4, "", HD)
                        ))
                        .build());

        assertThat(actual).isEqualTo(80);
    }
}
