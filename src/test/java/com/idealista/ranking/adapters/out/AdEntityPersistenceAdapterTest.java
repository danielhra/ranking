package com.idealista.ranking.adapters.out;

import com.idealista.ranking.adapters.out.vo.AdEntity;
import com.idealista.ranking.adapters.out.vo.PictureEntity;
import com.idealista.ranking.domain.Ad;
import com.idealista.ranking.motherobjects.AdEntityMother;
import com.idealista.ranking.motherobjects.AdMother;
import com.idealista.ranking.motherobjects.PictureEntityMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.blockhound.BlockHound;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static com.idealista.ranking.domain.Ad.Typology.GARAGE;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdEntityPersistenceAdapterTest {

    @Captor
    ArgumentCaptor<Flux<AdEntity>> adsCaptor;

    InMemoryPersistence inMemoryPersistence;
    AdPersistenceAdapter sut;
    private AdEntity adWithoutScore;
    private Flux<PictureEntity> pictures;
    private Ad adDomain;

    @BeforeEach
    void setUp() {
        BlockHound.install();
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

        StepVerifier
                .create(sut.getAdsWithoutScore())
                .expectNextMatches(ad -> ad.getScore() == null)
                .verifyComplete();
    }


    @Test
    void shouldMapToDomain() {
        when(inMemoryPersistence.getAds()).thenReturn(Flux.just(adWithoutScore));
        when(inMemoryPersistence.getPictures()).thenReturn(pictures);

        StepVerifier
                .create(sut.getAdsWithoutScore())
                .expectNextMatches(this::isMappedToDomainCorrectly)
                .verifyComplete();
    }

    public boolean isMappedToDomainCorrectly(Ad ad) {
        StepVerifier.create(ad.getPictures())
                .expectNextCount(2)
                .verifyComplete();

        return ad.getScore() == null &&
                ad.getDescription().equals(adWithoutScore.getDescription()) &&
                ad.getGardenSize().equals(adWithoutScore.getGardenSize()) &&
                ad.getHouseSize().equals(adWithoutScore.getHouseSize()) &&
                ad.getIrrelevantSince() == null &&
                ad.getTypology().getCode().equals(adWithoutScore.getTypology());

    }

    @Test
    void shouldMapToEntity() {
        adDomain = AdMother.getPrebuiltAd().typology(GARAGE).build();
        sut.saveAll(Flux.just(adDomain));

        verify(inMemoryPersistence).saveAds(adsCaptor.capture());


        StepVerifier
                .create(adsCaptor.getValue())
                .expectNextMatches(this::isMappedToEntityCorrectly)
                .verifyComplete();

    }

    private boolean isMappedToEntityCorrectly(AdEntity ad) {
        StepVerifier.create(ad.getPictures())
                .expectNext(1,2)
                .verifyComplete();

        return ad.getScore() == null &&
                ad.getDescription().equals(adDomain.getDescription()) &&
                ad.getGardenSize().equals(adDomain.getGardenSize()) &&
                ad.getHouseSize().equals(adDomain.getHouseSize()) &&
                ad.getIrrelevantSince() == null &&
                ad.getTypology().equals(adDomain.getTypology().getCode());
    }

    @Test
    void shouldGetAdsWithAScoreGreaterThan40() {
        when(inMemoryPersistence.getAds()).thenReturn(Flux.just(
                adWithoutScore,
                AdEntityMother.createAdWithScore()
        ));

        StepVerifier
                .create(sut.getAdsWithScoreGreaterOrEqualThan(40))
                .expectNextMatches(ad -> ad.getScore() >= 40)
                .verifyComplete();

    }
}

