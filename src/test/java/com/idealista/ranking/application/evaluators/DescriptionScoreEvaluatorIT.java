package com.idealista.ranking.application.evaluators;

import com.idealista.ranking.domain.Ad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.blockhound.BlockHound;
import reactor.test.StepVerifier;

import static com.idealista.ranking.domain.Ad.Typology.CHALET;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class DescriptionScoreEvaluatorIT {

    @Autowired
    DescriptionScoreEvaluator sut;

    @BeforeEach
    void setUp() {
        BlockHound.install();
    }

    @Test
    void shouldAdd5PointsIfItHasDescription() {
        StepVerifier.create(
                sut.evaluate(
                        Ad.builder()
                                .description("description")
                                .build()
                )).expectNext(5)
                .verifyComplete();
    }

    @Test
    void shouldAddPointsForEachDescriptionChain() {
        StepVerifier.create(
                sut.evaluate(
                        Ad.builder()
                                .typology(CHALET)
                                .description("Nuevo ático céntrico recién reformado. No deje pasar la oportunidad y adquiera este ático de lujo")
                                .build()
                ))
                .expectNext(30)
                .verifyComplete();

    }
}
