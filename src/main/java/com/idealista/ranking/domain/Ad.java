package com.idealista.ranking.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.Date;
import java.util.stream.Stream;

@Value
@Builder
public class Ad {

    Integer id;
    Typology typology;
    String description;
    Flux<Picture> pictures;
    Integer houseSize;
    Integer gardenSize;

    @With
    Integer score;
    LocalDate irrelevantSince;

    public enum Typology {
        FLAT("PISO"),
        CHALET("CHALET"),
        GARAGE("GARAGE");

        private final String code;

        Typology(String code) {
            this.code = code;
        }

        public static Typology of(String value) {
            return Stream.of(values()).filter(typology -> typology.code.equals(value)).findFirst().orElseThrow();
        }

        public String getCode() {
            return code;
        }
    }
}
