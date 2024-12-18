package ff.cimex.chrifacile.enums;

import lombok.Getter;

@Getter
public enum Type {

    TYPE_TERRAIN_AGRICOLE("Terrain agricole"), TYPE_TERRAIN_URBAIN("TerrainUrbain"), TYPE_BIEN_IMMOBILIER("Bien immobilier"),
    TYPE_APPARTEMENT("Appartement"), TYPE_VILLA("Villa"),TYPE_IMMEUBLE("Immeuble"),TYPE_RIAD("Riade");

    private final String name;

    Type(String name) {
        this.name=name;
    }
}
