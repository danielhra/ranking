package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.domain.Picture;
import com.idealista.ranking.motherobjects.AdMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.blockhound.BlockHound;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static com.idealista.ranking.domain.Picture.Quality.HD;
import static com.idealista.ranking.domain.Picture.Quality.SD;

class PictureScoreEvaluatorTest {

    PictureScoreEvaluator sut;

    @BeforeEach
    void setUp() {
        BlockHound.install();
        sut = new PictureScoreEvaluator();
    }

    @Test
    void shouldEvaluateNegativeIfItDoesntHavePictures() {

        StepVerifier.create(sut.evaluate(AdMother
                .getPrebuiltAd()
                .pictures(null)
                .build()))
                .expectNext(-10)
                .verifyComplete();

        StepVerifier.create(sut.evaluate(AdMother
                .getPrebuiltAd()
                .pictures(Flux.just())
                .build()))
                .expectNext(-10)
                .verifyComplete();


    }

    @Test
    void shouldGive10PointsForNoHdPicture() {

        StepVerifier.create(
                sut.evaluate(
                        AdMother.getPrebuiltAd()
                                .pictures(Flux.just(
                                        new Picture(1, "", SD),
                                        new Picture(2, "", SD),
                                        new Picture(3, "", SD),
                                        new Picture(4, "", SD)
                                ))
                                .build()))
                .expectNext(40)
                .verifyComplete();
    }

    @Test
    void shouldGive20PointsForHdPicture() {
        StepVerifier.create(
                sut.evaluate(
                        AdMother.getPrebuiltAd()
                                .pictures(Flux.just(
                                        new Picture(1, "", HD),
                                        new Picture(2, "", HD),
                                        new Picture(3, "", HD),
                                        new Picture(4, "", HD)
                                ))
                                .build()))
                .expectNext(80)
                .verifyComplete();

    }
}

