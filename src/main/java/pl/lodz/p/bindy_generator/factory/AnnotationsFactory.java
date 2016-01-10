package pl.lodz.p.bindy_generator.factory;

import com.squareup.javapoet.AnnotationSpec;
import pl.lodz.p.bindy_generator.params.MainParams;
import pl.lodz.p.bindy_generator.util.Utils;

import java.util.Map;

/**
 * Created by maciek on 09/01/16.
 */
public class AnnotationsFactory {

    public static AnnotationSpec getAnnotation(Class aClass, MainParams command) {
        AnnotationSpec.Builder annotationBuilder = AnnotationSpec.builder(aClass);

        Map<String, Object> annotationsMembersCsvRecord = command.getAnnotationMembers(aClass);
        for (Map.Entry<String, Object> entry : annotationsMembersCsvRecord.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            Object defaultValue = Utils.getAnnotationDefault(aClass, key);

            if (value != null && (defaultValue == null || !defaultValue.equals(value))) {
                if (value instanceof String) {
                    annotationBuilder.addMember(key, "$S", value);
                } else {
                    annotationBuilder.addMember(key, "$L", value);
                }
            }
        }

        return annotationBuilder.build();
    }


}
