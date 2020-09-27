package com.idealista.ranking.motherobjects;

import com.idealista.ranking.adapters.out.vo.PictureEntity;
import com.idealista.ranking.domain.Picture;
import reactor.core.publisher.Flux;

public class PictureEntityMother {
    public static Flux<PictureEntity> createFluxOf() {
        return Flux.range(0, 5)
                .map(integer -> new PictureEntity(integer, "mockUrl", Picture.Quality.HD.name()));
    }
}
