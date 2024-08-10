package ff.cimex.chrifacile.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CompareUtil {

    public static boolean allNonNull(Object... objs) {
        for (Object obj : objs) {
            if (obj == null) {
                return false;
            }
        }
        return true;
    }

    public static boolean oneIsNull(Object... objs) {
        for (Object obj : objs) {
            if (obj == null) {
                return true;
            }
        }
        return false;
    }
}
