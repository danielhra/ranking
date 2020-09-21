package com.idealista.ranking.adapters.out;

import com.idealista.ranking.adapters.out.vo.AdEntity;
import com.idealista.ranking.adapters.out.vo.PictureEntity;
import com.idealista.ranking.domain.Ad;
import com.idealista.ranking.domain.Picture;
import com.idealista.ranking.motherobjects.AdEntityMother;
import com.idealista.ranking.motherobjects.PictureEntityMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class AdEntityPersistenceAdapterTest {

    InMemoryPersistence inMemoryPersistence;
    AdPersistenceAdapter sut;
    private AdEntity adWithoutScore;
    private Flux<PictureEntity> pictures;

    @BeforeEach
    void setUp() {

        inMemoryPersistence = mock(InMemoryPersistence.class);
        sut = new AdPersistenceAdapter(inMemoryPersistence);

        adWithoutScore = AdEntityMother.createAdWithoutScore();
        pictures = PictureEntityMother.createFluxOf();

    }

    @Test
    void shouldOnlyReturnAdsWithoutScore() {

        when(inMemoryPersistence.getAds()).thenReturn(Flux.just(
                adWithoutScore,
                AdEntityMother.createAdWithScore()
        ));

        when(inMemoryPersistence.getPictures()).thenReturn(pictures);


        StepVerifier
                .create(sut.getAdsWithoutScore())
                .expectNextMatches(ad -> ad.getScore() == null)
                .verifyComplete();
    }


    @Test
    void shouldMapToEntityObject() {
        when(inMemoryPersistence.getAds()).thenReturn(Flux.just(adWithoutScore));
        when(inMemoryPersistence.getPictures()).thenReturn(pictures);

        StepVerifier
                .create(sut.getAdsWithoutScore())
                .expectNextMatches(this::isMappedToEntityCorrectly)
                .verifyComplete();
    }

    public boolean isMappedToEntityCorrectly(Ad ad) {
        return ad.getScore() == null &&
                ad.getDescription().equals(adWithoutScore.getDescription()) &&
                ad.getGardenSize() == null &&
                ad.getHouseSize().equals(adWithoutScore.getHouseSize()) &&
                ad.getIrrelevantSince() == null &&
                ad.getTypology() == null &&
                checkPictures(ad);

    }

    private boolean checkPictures(Ad ad) {
        return pictures
                .map(pictureEntity -> new Picture(pictureEntity.getId(), pictureEntity.getUrl(), pictureEntity.getQuality()))
                .toStream()
                .collect(Collectors.toList())
                .containsAll(ad.getPictures());
    }

}
