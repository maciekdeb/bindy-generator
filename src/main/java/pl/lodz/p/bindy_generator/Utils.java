package pl.lodz.p.bindy_generator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by maciek on 06/01/16.
 */
public class Utils {

    @SuppressWarnings("unchecked")
    public static<T> T getAnnotationDefault(Class<? extends Annotation> annotationClass, String element) throws Exception {
        Method method = annotationClass.getMethod(element,(Class[])null);
        return((T)method.getDefaultValue());
    }

}
