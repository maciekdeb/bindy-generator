package pl.lodz.p.params.property;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import pl.lodz.p.util.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maciek on 11/01/16.
 */
public class DataFieldPropertyParams implements AnnotationsPropertyParams {

    private static final Class CLASS = DataField.class;

    private Map<String, String> parameters;

    private Integer pos = Utils.<Integer>getAnnotationDefault(CLASS, "pos");
    private String pattern = Utils.<String>getAnnotationDefault(CLASS, "pattern");
    private Integer length = Utils.<Integer>getAnnotationDefault(CLASS, "length");
    private Integer precision = Utils.<Integer>getAnnotationDefault(CLASS, "precision");
    private Integer position = Utils.<Integer>getAnnotationDefault(CLASS, "position");
    private Boolean required = Utils.<Boolean>getAnnotationDefault(CLASS, "required");
    private Boolean trim = Utils.<Boolean>getAnnotationDefault(CLASS, "trim");
    private String defaultValue = Utils.<String>getAnnotationDefault(CLASS, "defaultValue");
    private Boolean impliedDecimalSeparator = Utils.<Boolean>getAnnotationDefault(CLASS, "impliedDecimalSeparator");
    private Integer lengthPos = Utils.<Integer>getAnnotationDefault(CLASS, "lengthPos");
    private String delimiter = Utils.<String>getAnnotationDefault(CLASS, "delimiter");

    public DataFieldPropertyParams(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public Map<String, Object> getAnnotationsMembers(int pos) {
        String params = parameters.get(String.valueOf(pos));

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

        meldValues(params, result);

        initializeRequiredFields(result, pos);

        return result;
    }

    private void meldValues(String params, Map<String, Object> result) {
        for (Map.Entry<String, Object> entry : result.entrySet()) {
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

    private void initializeRequiredFields(Map<String, Object> result, int pos) {
        if (result.get("pos") == null) {
            result.put("pos", pos);
        }
    }

}
