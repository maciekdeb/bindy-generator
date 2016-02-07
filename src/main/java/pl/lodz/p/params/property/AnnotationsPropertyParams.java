package pl.lodz.p.params.property;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by maciek on 11/01/16.
 */
public interface AnnotationsPropertyParams {

    String LIST_SEPARATOR = ",";

    default String getValue(String params, String fieldName) {
        if (StringUtils.isNoneBlank(params)) {
            Matcher m = Pattern.compile(fieldName + "\\(([^)]+)\\)").matcher(params);
            if (m.find()) {
                return m.group(1);
            }
            if (params.startsWith(fieldName + LIST_SEPARATOR) || params.endsWith(LIST_SEPARATOR + fieldName)) {
                return String.valueOf(true);
            }
        }
        return null;
    }

    Map<String, Object> getAnnotationsMembers(int pos);

}
