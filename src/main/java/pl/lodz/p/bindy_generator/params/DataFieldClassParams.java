package pl.lodz.p.bindy_generator.params;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.format.NumberPatternFormat;
import pl.lodz.p.bindy_generator.util.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maciek on 11/01/16.
 */
public class DataFieldClassParams implements AnnotationsPropertyParams {

    private Map<Integer, String> parameters;

    private Class aClass = DataField.class;

    private int pos;
    private String pattern;
    private int length;
    private int precision;
    private int position;
    private boolean required;
    private boolean trim;
    private String defaultValue;
    private boolean impliedDecimalSeparator;
    private int lengthPos;
    private String delimiter;

    public DataFieldClassParams(Map<Integer, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public Map<String, Object> getAnnotationsMembers(int pos) {
        String params = parameters.get(pos);

        Map<String, Object> result = new HashMap<>();
        result.put("pos", pos);
        result.put("pattern", pattern);
        result.put("length", length);
        result.put("precision", precision);
        result.put("position", position);
        result.put("required", required);
        result.put("trim", trim);
        result.put("defaultValue", defaultValue);
        result.put("impliedDecimalSeparator", impliedDecimalSeparator);
        result.put("lengthPos", lengthPos);
        result.put("delimiter", delimiter);

        for (Map.Entry<String, Object> entry : result.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            String temp = getValue(params, key);
            if (value instanceof String) {
                if (temp != null) {
                    result.put(key, temp);
                } else {
                    result.put(key, Utils.getAnnotationDefault(aClass, key));
                }
            } else if (value instanceof Number) {
                if (temp != null) {
                    result.put(key, Integer.parseInt(temp));
                } else {
                    result.put(key, (Integer) Utils.getAnnotationDefault(aClass, key));
                }
            } else if (value instanceof Boolean) {
                if (temp != null) {
                    result.put(key, Boolean.valueOf(temp));
                } else {
                    result.put(key, (Boolean) Utils.getAnnotationDefault(aClass, key));
                }
            }
        }

        if (result != null && result.get("pos") == null) {
            result.put("pos", pos);
        }

        return result;
    }

}
