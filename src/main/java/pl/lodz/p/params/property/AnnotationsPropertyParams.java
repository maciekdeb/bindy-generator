package pl.lodz.p.params.property;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import pl.lodz.p.util.Utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by maciek on 11/01/16.
 */
public abstract class AnnotationsPropertyParams {

    protected Map<String, Object> result;

    void initializeResultMapTemplate(String fields, Map<String, Object> generated) {
        this.result = new HashMap<>();
        this.initializeFields(generated);
        this.meldDefaultValuesWithGiven(fields);
        this.reduceUselessDefaultFields();
    }

    abstract void initializeFields(Map<String, Object> generated);

    public Map<String, Object> getAnnotationsMembers(String fields, int position) {
        Map<String, Object> generated = new HashMap<>();
        generated.put("pos", position);
        this.initializeResultMapTemplate(fields, generated);
        return this.result;
    }

    void reduceUselessDefaultFields() {
        Iterator<Map.Entry<String, Object>> i = result.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry<String, Object> entry = i.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            Object defaultValue = Utils.getAnnotationDefault(getType(), entry.getKey());
            if (!isFieldRequired(key) && (value != null && value.equals(defaultValue))) {
                i.remove();
            }
        }
    }

    abstract Class getType();

    protected boolean isFieldRequired(String key) {
        return getRequiredFields().contains(key);
    }

    abstract List<String> getRequiredFields();

    public String getValue(String params, String fieldName) {
        final String LIST_SEPARATOR = ",";
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

    private void meldDefaultValuesWithGiven(String params) {
        for (Map.Entry<String, Object> entry : this.result.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            String temp = getValue(params, key);
            if (temp != null) {
                if (value instanceof String) {
                    result.put(key, temp);
                } else if (value instanceof Number) {
                    result.put(key, Integer.parseInt(temp));
                } else if (value instanceof Boolean) {
                    result.put(key, Boolean.valueOf(temp));
                }
            }
        }
    }

}
