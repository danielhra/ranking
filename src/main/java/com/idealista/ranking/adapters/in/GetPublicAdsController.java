package com.idealista.ranking.adapters.in;

import com.idealista.ranking.adapters.in.dto.PublicAd;
import com.idealista.ranking.application.ports.in.GetPublicAdsUseCase;
import com.idealista.ranking.domain.Ad;
import com.idealista.ranking.domain.Picture;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
public class GetPublicAdsController {

    private final GetPublicAdsUseCase getPublicAdsUseCase;

    @GetMapping("/v1/ads/public")
    public Flux<PublicAd> getPublicAds() {
        return getPublicAdsUseCase.getPublicAds().map(this::toDto);
    }

    private PublicAd toDto(Ad ad) {

        return new PublicAd(ad.getId(),
                ad.getTypology().getCode(),
                ad.getDescription(),
                mapToDto(ad.getPictures()),
                ad.getHouseSize(),
                ad.getGardenSize());

    }

    private Flux<String> mapToDto(Flux<Picture> pictures) {
        return pictures.map(Picture::getUrl);
    }
}
