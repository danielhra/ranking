package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.application.evaluators.chain.ChaletDescriptionScoreEvaluator;
import com.idealista.ranking.application.evaluators.chain.DescriptionEvaluator;
import com.idealista.ranking.domain.Ad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class ChaletDescriptionScoreEvaluatorChainTest {


    DescriptionEvaluator handler;

    @BeforeEach
    void setUp() {
        handler = new ChaletDescriptionScoreEvaluator();
    }

    @Test
    void shouldAdd20PointsIfItsAChaletAndItHasMoreThanOrEqual50words() {
        StepVerifier.create(
                handler.evaluate(
                        Mono.just(Ad.builder()
                                .typology(Ad.Typology.CHALET)
                                .description("this is a very long description that provides no information")
                                .build()
                        ))).expectNext(20).verifyComplete();

    }

    @Test
    void shouldNotAddExtraPointsIfChaletHasLessThan50Words() {
        StepVerifier.create(
                handler.evaluate(
                        Mono.just(
                                Ad.builder()
                                        .typology(Ad.Typology.CHALET)
                                        .description("this description not long enough")
                                        .build()
                        ))).expectNext(0).verifyComplete();
    }

    @Test
    void shouldNotAddMorePointsIfItsNotAChalet() {

        StepVerifier.create(
                handler.evaluate(
                        Mono.just(Ad.builder()
                                .typology(Ad.Typology.GARAGE)
                                .description("this is a very long description that provides no information")
                                .build()
                        ))).verifyComplete();
    }

}
