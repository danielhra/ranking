package com.idealista.ranking.application.ports.in;

import com.idealista.ranking.domain.Ad;
import reactor.core.publisher.Flux;

public interface GetPublicAdsUseCase {

    Flux<Ad> getPublicAds();
}
