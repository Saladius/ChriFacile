package ff.cimex.chrifacile.enums;

import lombok.Getter;

@Getter
public enum Role {

    ROLE_ACHETEUR("acheteur"), ROLE_VENDEUR("vendeur"), ROLE_ADMIN("admin"), ROLE_SUPERADMIN("super-admin");

    private final String name;

    Role(String name) {
        this.name=name;
    }
}
