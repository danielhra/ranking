package com.idealista.ranking.adapters.out;

import com.idealista.ranking.adapters.out.vo.AdEntity;
import com.idealista.ranking.adapters.out.vo.PictureEntity;
import com.idealista.ranking.domain.Ad;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public class InMemoryPersistence {

    public static final String CHALET = "CHALET";
    public static final String FLAT = "PISO";
    public static final String GARAGE = "GARAGE";
    public static final String SD = "SD";
    public static final String HD = "HD";
    private Flux<AdEntity> ads;
    private Flux<PictureEntity> pictures;

    public InMemoryPersistence() {

        ads = Flux.just(
                new AdEntity(1, CHALET, "Este piso es una ganga, compra, compra, COMPRA!!!!!", Flux.just(), 300, null, null, null),
                new AdEntity(2, FLAT, "Nuevo ático céntrico recién reformado. No deje pasar la oportunidad y adquiera este ático de lujo", Flux.just(4), 300, null, null, null),
                new AdEntity(3, CHALET, "", Flux.just(2), 300, null, null, null),
                new AdEntity(4, FLAT, "Ático céntrico muy luminoso y recién reformado, parece nuevo", Flux.just(5), 300, null, null, null),
                new AdEntity(5, FLAT, "Pisazo,", Flux.just(3, 8), 300, null, null, null),
                new AdEntity(6, GARAGE, "", Flux.just(6), 300, null, null, null),
                new AdEntity(7, GARAGE, "Garaje en el centro de Albacete", Flux.just(), 300, null, null, null),
                new AdEntity(8, CHALET, "Maravilloso chalet situado en lAs afueras de un pequeño pueblo rural. El entorno es espectacular, las vistas magníficas. ¡Cómprelo ahora!", Flux.just(1, 7), 300, null, null, null)
        );

        pictures = Flux.just(
                new PictureEntity(1, "http://www.idealista.com/pictures/1", SD),
                new PictureEntity(2, "http://www.idealista.com/pictures/2", HD),
                new PictureEntity(3, "http://www.idealista.com/pictures/3", SD),
                new PictureEntity(4, "http://www.idealista.com/pictures/4", HD),
                new PictureEntity(5, "http://www.idealista.com/pictures/5", SD),
                new PictureEntity(6, "http://www.idealista.com/pictures/6", SD),
                new PictureEntity(7, "http://www.idealista.com/pictures/7", SD),
                new PictureEntity(8, "http://www.idealista.com/pictures/8", HD)
        );

    }


    public Flux<AdEntity> getAds() {
        return ads;
    }

    public Flux<PictureEntity> getPictures() {
        return pictures;
    }

    public void saveAds(Flux<AdEntity> ads) {
        this.ads = ads;

    }
}
