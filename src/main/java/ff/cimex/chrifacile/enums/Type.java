package ff.cimex.chrifacile.enums;

import lombok.Getter;

@Getter
public enum Type {

    TYPE_APPARTEMENT("appartement"), TYPE_TERRAIN_AGRICOLE("Terrain agricole"), TYPE_TERRAIN_URBAIN("TerrainUrbain"),TYPE_VILLA("villa");

    private final String name;

    Type(String name) {
        this.name=name;
    }
}
