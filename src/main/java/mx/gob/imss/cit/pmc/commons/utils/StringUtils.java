package mx.gob.imss.cit.pmc.commons.utils;

import org.bson.types.ObjectId;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.Date;

public class StringUtils {

    public static String safetyValidateString(String str) {
        return isValid(str) ? str : "";
    }

    public static String safetyValidateStringE(String str) {
        return isValid(str) ? str : "";
    }

    public static String safetyValidateInteger(Integer integer) {
        return NumberUtils.isValid(integer, Boolean.TRUE) ? integer.toString() : "";
    }
    
    public static String safetyValidateIntegerString(Integer integer) {
        return NumberUtils.isValid(integer, Boolean.TRUE) ? integer.toString() : "";
    }


    public static String safeValidateDate(Date date) {
        return date != null ? date.toString() : "";
    }

    public static String safeValidateBigDecimal(BigDecimal decimal) {
        return decimal != null ? decimal.toString() : "";
    }

    public static String safeValidateObjectId(ObjectId objectId) {
        return objectId != null ? objectId.toString() : null;
    }

    public static boolean isNotBlank(@Nullable String string) {
        return !isBlank(string);
    }

    public static boolean isValid(String str) {
        return str == null ? Boolean.FALSE : !(isBlank(str) || isEmpty(str) || str.equals("-1"));
    }

    public static boolean isEmpty(@Nullable String string) {
        return string == null || string.isEmpty();
    }

    public static boolean isNotEmpty(@Nullable String string) {
        return !isEmpty(string);
    }

    public static boolean isBlank(@Nullable String string) {
        if (isEmpty(string)) {
            return true;
        } else {
            for(int i = 0; i < string.length(); ++i) {
                if (!Character.isWhitespace(string.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }
    
    public static boolean validaFolioST3Laudo(@Nullable String string) {
    	if(string.length() != 17) {
    		return false;
    	}
    	
    	return string.matches("^[0]{8}$");
    }

}
