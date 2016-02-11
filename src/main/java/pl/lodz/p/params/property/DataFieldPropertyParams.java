package pl.lodz.p.params.property;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import pl.lodz.p.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by maciek on 11/01/16.
 */
public class DataFieldPropertyParams extends AnnotationsPropertyParams {

    private Integer pos = Utils.<Integer>getAnnotationDefault(getType(), "pos");
    private String pattern = Utils.<String>getAnnotationDefault(getType(), "pattern");
    private Integer length = Utils.<Integer>getAnnotationDefault(getType(), "length");
    private Integer precision = Utils.<Integer>getAnnotationDefault(getType(), "precision");
    private Integer position = Utils.<Integer>getAnnotationDefault(getType(), "position");
    private Boolean required = Utils.<Boolean>getAnnotationDefault(getType(), "required");
    private Boolean trim = Utils.<Boolean>getAnnotationDefault(getType(), "trim");
    private String defaultValue = Utils.<String>getAnnotationDefault(getType(), "defaultValue");
    private Boolean impliedDecimalSeparator = Utils.<Boolean>getAnnotationDefault(getType(), "impliedDecimalSeparator");
    private Integer lengthPos = Utils.<Integer>getAnnotationDefault(getType(), "lengthPos");
    private String delimiter = Utils.<String>getAnnotationDefault(getType(), "delimiter");

    @Override
    void initializeFields(Map<String, Object> generated) {
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
        result.putAll(generated);
    }

    @Override
    Class getType() {
        return DataField.class;
    }

    @Override
    List<String> getRequiredFields() {
        List<String> requiredFields = new ArrayList<>();
        requiredFields.add("pos");
        return requiredFields;
    }
//    }

//    private void initializeRequiredFields(Map<String, Object> result, int pos) {
//        if (result.get("pos") == null) {
//            result.put("pos", pos);
//        }
//    }

}
