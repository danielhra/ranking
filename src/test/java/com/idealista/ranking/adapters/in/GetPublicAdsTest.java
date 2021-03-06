package com.idealista.ranking.adapters.in;

import com.idealista.ranking.adapters.in.dto.PublicAd;
import com.idealista.ranking.application.ports.in.GetPublicAdsUseCase;
import com.idealista.ranking.domain.Ad;
import com.idealista.ranking.domain.Picture;
import com.idealista.ranking.motherobjects.AdMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetPublicAdsControllerTest {

    GetPublicAdsController sut;
    GetPublicAdsUseCase getPublicAdsUseCase;
    private Ad domainAd;

    @BeforeEach
    void setUp() {
        getPublicAdsUseCase = mock(GetPublicAdsUseCase.class);
        sut = new GetPublicAdsController(getPublicAdsUseCase);
    }

    @Test
    void shouldMapToDto() {
        domainAd = AdMother.getPrebuiltAd().score(80).build();
        when(getPublicAdsUseCase.getPublicAds()).thenReturn(Flux.just(domainAd));

        StepVerifier.create(sut.getPublicAds())
                .expectNextMatches(this::mapsCorrectlyToDto)
                .verifyComplete();
    }

    private boolean mapsCorrectlyToDto(PublicAd publicAd) {
        final List<Picture> pictures = domainAd.getPictures().collectList().block();
        Objects.requireNonNull(pictures);
        assertThat(publicAd.getPictureUrls()).contains(pictures.get(0).getUrl(),pictures.get(0).getUrl());


        return publicAd.getDescription()
                .equals(domainAd.getDescription())
                && publicAd.getGardenSize().equals(domainAd.getGardenSize())
                && publicAd.getHouseSize().equals(domainAd.getHouseSize())
                && publicAd.getTypology().equals(domainAd.getTypology().getCode())
                && publicAd.getId().equals(domainAd.getId());
    }
}
