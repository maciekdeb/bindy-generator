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
        populateMembers(annotationBuilder, command.getAnnotationMembers(aClass));
        return annotationBuilder.build();
    }

    public static AnnotationSpec getAnnotation(Class aClass, MainParams command, int pos) {
        AnnotationSpec.Builder annotationBuilder = AnnotationSpec.builder(aClass);
        populateMembers(annotationBuilder, command.getAnnotationMembers(aClass, pos));
        return annotationBuilder.build();
    }

    public static AnnotationSpec.Builder populateMembers(AnnotationSpec.Builder builder, Map<String, Object> map) {
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                if (entry != null) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (value != null) {
                        if (value instanceof String) {
                            builder.addMember(key, "$S", value);
                        } else {
                            builder.addMember(key, "$L", value);
                        }
                    }
                }
            }
        }
        return builder;
    }

}
