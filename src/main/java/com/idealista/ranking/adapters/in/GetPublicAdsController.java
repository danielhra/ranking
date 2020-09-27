package com.idealista.ranking.adapters.in;

import com.idealista.ranking.adapters.in.dto.PublicAd;
import com.idealista.ranking.application.ports.in.GetPublicAdsUseCase;
import com.idealista.ranking.domain.Ad;
import com.idealista.ranking.domain.Picture;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@AllArgsConstructor
public class GetPublicAdsController {

    private final GetPublicAdsUseCase getPublicAdsUseCase;

    @GetMapping("/v1/ads/public")
    public Flux<PublicAd> getPublicAds() {
        return getPublicAdsUseCase.getPublicAds().flatMap(this::toDto);
    }

    private Mono<PublicAd> toDto(Ad ad) {

        return mapToDto(ad.getPictures()).map(pictureUrls ->
                new PublicAd(
                        ad.getId(),
                        ad.getTypology().getCode(),
                        ad.getDescription(),
                        pictureUrls,
                        ad.getHouseSize(),
                        ad.getGardenSize())

        );

    }

    private Mono<List<String>> mapToDto(Flux<Picture> pictures) {
        return pictures.map(Picture::getUrl).collectSortedList();
    }
}
