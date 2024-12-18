package ff.cimex.chrifacile.enums;

public enum TypeImmeuble {

    COMMERCIAL("Commercial"),INDUSTRIEL("Industriel"),RESIDENTIEL("Residentiel"),HOTELERIE("Hotelier");

    private final String name;

    TypeImmeuble(String name) {
        this.name=name;
    }
}
