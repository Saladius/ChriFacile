package ff.cimex.chrifacile.enums;

import lombok.Getter;

@Getter
public enum Unites {
    UNITES_METRE_CARREE("m²"),UNITES_HECTARE("hectare");

    private final String name;

    Unites(String name) {
        this.name=name;
    }
}
