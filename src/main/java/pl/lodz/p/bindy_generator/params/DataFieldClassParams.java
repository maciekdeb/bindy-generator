package pl.lodz.p.bindy_generator.params;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by maciek on 11/01/16.
 */
public class DataFieldClassParams implements AnnotationsPropertyParams {

    private Map<Integer, String> parameters;

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
        //// TODO: 11/01/16 Can be null - change it
        String params = parameters.get(pos);
        pattern = getValue(params, "pattern");
        length = Integer.parseInt(getValue(params, "length"));
        precision = Integer.parseInt(getValue(params, "precision"));
        position = Integer.parseInt(getValue(params, "position"));
        required = Boolean.parseBoolean(getValue(params, "position"));
        trim = Boolean.parseBoolean(getValue(params, "position"));
        defaultValue = getValue(params, "position");
        impliedDecimalSeparator = Boolean.parseBoolean(getValue(params, "position"));
        lengthPos = Integer.parseInt(getValue(params, "position"));
        delimiter = getValue(params, "position");

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
        return result;
    }

    public static String getValue(String params, String fieldName) {
        Matcher m = Pattern.compile(fieldName + "\\(([^)]+)\\)").matcher(params);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }
}
