package com.idealista.ranking.application.ports.out;

import com.idealista.ranking.domain.Ad;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface CalculateScoreRepository {

    Flux<Ad> getAdsWithoutScore();

    void saveAll(Flux<Ad> ads);
}
