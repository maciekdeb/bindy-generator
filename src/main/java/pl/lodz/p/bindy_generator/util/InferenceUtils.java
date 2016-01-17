package pl.lodz.p.bindy_generator.util;

import java.util.List;

/**
 * Created by maciek on 17/01/16.
 */
public class InferenceUtils {

    public static Class inferFieldType(List<String> lines, String separator, int position) {

        boolean everyIsDecimal = true;
        boolean everyIsInteger = true;

        for (String line : lines) {
            String[] fields = line.split(separator);
            String field = fields[position].trim();
            if (everyIsDecimal && !isDecimal(field)) {
                everyIsDecimal = false;
            }
            if (everyIsInteger && !isInteger(field)) {
                everyIsInteger = false;
            }
        }

        if (everyIsDecimal) {
            return Double.class;
        } else if (everyIsInteger) {
            return Integer.class;
        } else {
            return String.class;
        }
    }

    public static boolean isDecimal(String value) {
        return matchRegex(value, "(\\+|-)?([0-9]+(\\.[0-9]+))");
    }

    public static boolean isInteger(String value) {
        return matchRegex(value, "(\\+|-)?(\\d)+");
    }

    private static boolean matchRegex(String value, String regex) {
        return value.matches(regex);
    }

}
