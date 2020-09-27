package com.idealista.ranking.adapters.out;

import com.idealista.ranking.adapters.out.vo.AdEntity;
import com.idealista.ranking.application.ports.out.CalculateScoreRepository;
import com.idealista.ranking.domain.Ad;
import com.idealista.ranking.domain.Picture;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import static com.idealista.ranking.domain.Ad.Typology.*;

@Repository
public class AdPersistenceAdapter implements CalculateScoreRepository {

    private final InMemoryPersistence inMemoryPersistence;

    public AdPersistenceAdapter(InMemoryPersistence inMemoryPersistence) {
        this.inMemoryPersistence = inMemoryPersistence;
    }

    @Override
    public Flux<Ad> getAdsWithoutScore() {
        return inMemoryPersistence
                .getAds()
                .filter(this::hasScore)
                .map(this::toDomain);
    }

    @Override
    public Ad save(Ad ad) {
        System.out.println("ad = " + ad);
        return null;
    }

    private Ad toDomain(AdEntity adEntity) {

        return Ad.builder()
                .description(adEntity.getDescription())
                .gardenSize(adEntity.getGardenSize())
                .houseSize(adEntity.getHouseSize())
                .id(adEntity.getId())
                .pictures(toDomain(adEntity.getPictures()))
                .typology(of(adEntity.getTypology()))
                .build();


    }

    private Flux<Picture> toDomain(Flux<Integer> pictureIds) {

        return pictureIds
                .flatMap(pictureId -> inMemoryPersistence
                        .getPictures()
                        .filter(pictureEntity -> pictureEntity.getId().equals(pictureId)))
                .map(pictureEntity -> new Picture(pictureEntity.getId(), pictureEntity.getUrl(), Picture.Quality.valueOf(pictureEntity.getQuality())));

    }


    private Boolean hasScore(AdEntity ad) {
        return ad.getScore() == null;

    }
}

