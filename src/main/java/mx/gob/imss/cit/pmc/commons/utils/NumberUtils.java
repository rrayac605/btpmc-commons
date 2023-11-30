package mx.gob.imss.cit.pmc.commons.utils;

import java.math.BigDecimal;
import java.util.Arrays;

public class NumberUtils {

	public static final Integer MINUS_ONE = -1;

	public static Integer safetyValidateInteger(Integer integer) {
		return integer == null ? null : integer.equals(MINUS_ONE) ? null : integer;
	}
	
	public static String safetyValidateIntegerString(Integer integer) {
		return integer == null ? null : integer.equals(MINUS_ONE) ? null : integer.toString();
	}

	public static BigDecimal safetyValidateBigDecimal(BigDecimal bigDecimal) {
		return bigDecimal == null ? null : bigDecimal.equals(BigDecimal.valueOf(MINUS_ONE)) ? null : bigDecimal;
	}
	
	public static String safetyValidateBigDecimalString(BigDecimal bigDecimal) {
		return bigDecimal == null ? null : bigDecimal.equals(BigDecimal.valueOf(MINUS_ONE)) ? null : bigDecimal.toString();
	}

	public static BigDecimal safetyValidateBigDecimalZ(BigDecimal bigDecimal) {
		return bigDecimal == null ? new BigDecimal(0) : bigDecimal.equals(BigDecimal.valueOf(MINUS_ONE)) ?
				new BigDecimal(0) : bigDecimal;
	}

    public static boolean isValid(Integer integer, boolean grateThanZero) {
        return integer == null ? Boolean.FALSE :
                minorThanZero(integer) ? Boolean.FALSE :
                grateThanZero ? graterThanZero(integer) : Boolean.TRUE;
    }

    public static boolean minorThanZero(Integer integer) {
        return integer != null && integer.compareTo(0) < 0;
    }

    public static boolean graterThanZero(Integer integer) {
        return integer != null && integer.compareTo(0) > 0;
    }

	public static Long safetyParseLong(String longString) {
		String numbers = "0123456789";
		return longString != null && !longString.trim().equals("")
				&& Arrays.stream(longString.split("")).allMatch(numbers::contains) ? Long.parseLong(longString) : 0L;
	}

	public static Integer longToInt(Long l) {
		return l != null ? Math.toIntExact(l) : 0;
	}

    public static Integer safetyParseInteger(String integerString) {
        String numbers = "0123456789";
        return integerString != null && Arrays.stream(integerString.split("")).allMatch(numbers::contains) ? Integer.parseInt(integerString) : null;
    }

}
