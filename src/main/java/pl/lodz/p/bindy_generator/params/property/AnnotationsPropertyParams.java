package pl.lodz.p.bindy_generator.params.property;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by maciek on 11/01/16.
 */
public interface AnnotationsPropertyParams {

    default String getValue(String params, String fieldName) {
        if (StringUtils.isNoneBlank(params)) {
            Matcher m = Pattern.compile(fieldName + "\\(([^)]+)\\)").matcher(params);
            if (m.find()) {
                return m.group(1);
            }
        }
        return null;
    }

    public Map<String, Object> getAnnotationsMembers(int pos);

}
