package com.idealista.ranking.adapters.in;

import com.idealista.ranking.application.ports.in.CalculateScoreUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = CalculateScoreController.class)
class calculateScoreControllerIt {

    @MockBean
    CalculateScoreUseCase calculateScoreUseCase;

    @Autowired
    private WebTestClient client;


    @Test
    void shouldReturnOkWithoutBody() {

        client.post()
                .uri("/v1/score/calculate")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Void.class);
    }
}
