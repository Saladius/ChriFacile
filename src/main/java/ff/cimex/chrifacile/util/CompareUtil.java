package ff.cimex.chrifacile.util;

import io.micrometer.common.util.StringUtils;
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

    public static boolean oneStringIsEmpty(String... str){
        for(String s : str){
            if(StringUtils.isEmpty(s)){
                return true;
            }
        }
        return false;
    }

    public static boolean oneLongIsZero(Long... longs){
        for(Long l : longs){
            if(l == 0){
                return true;
            }
        }
        return false;
    }

    public static boolean oneIntegerIsZero(Integer... ints){
        for(Integer i : ints){
            if(i == 0){
                return true;
            }
        }
        return false;
    }

    public static boolean oneDoubleIsZero(Double... doubles){
        for(Double d : doubles){
            if(d == 0){
                return true;
            }
        }
        return false;
    }

}
