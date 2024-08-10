package ff.cimex.chrifacile.enums;

import lombok.Getter;

@Getter
public enum Unites {
    UNITES_METRE_CARREE("métre carrée"),UNITES_HECTARE("héctare");

    private final String name;

    Unites(String name) {
        this.name=name;
    }
}
