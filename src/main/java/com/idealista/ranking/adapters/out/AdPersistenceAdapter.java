package com.idealista.ranking.adapters.out;

import com.idealista.ranking.adapters.out.vo.AdEntity;
import com.idealista.ranking.application.ports.out.CalculateScoreRepository;
import com.idealista.ranking.domain.Ad;
import com.idealista.ranking.domain.Picture;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

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

    private Ad toDomain(AdEntity adEntity) {

        return Ad.builder()
                .description(adEntity.getDescription())
                .gardenSize(adEntity.getGardenSize())
                .houseSize(adEntity.getHouseSize())
                .id(adEntity.getId())
                .pictures(toDomain(adEntity.getPictures()))
                .build();


    }

    private List<Picture> toDomain(List<Integer> pictures) {
        return inMemoryPersistence
                .getPictures()
                .filter(pictureEntity -> pictures.contains(pictureEntity.getId()))
                .map(pictureEntity -> new Picture(pictureEntity.getId(), pictureEntity.getUrl(), pictureEntity.getQuality()))
                .toStream()
                .collect(Collectors.toList());
    }


    private Boolean hasScore(AdEntity ad) {
        return ad.getScore() == null;

    }
}

