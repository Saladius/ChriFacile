package ff.cimex.chrifacile.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@UtilityClass
public class DateUtil {

    public static Date ajouterJours(int nombreDeJours) {
        LocalDate dateActuelle = LocalDate.now();
        LocalDate nouvelleDate = dateActuelle.plusDays(nombreDeJours);
        return Date.from(nouvelleDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
