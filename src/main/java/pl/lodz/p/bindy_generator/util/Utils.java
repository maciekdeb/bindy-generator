package pl.lodz.p.bindy_generator.util;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import org.apache.commons.lang3.text.WordUtils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by maciek on 06/01/16.
 */
public class Utils {

    public static final String EMPTY_STRING = "";
    public static final String WHITE_SPACE = "\\s+";

    @SuppressWarnings("unchecked")
    public static <T> T getAnnotationDefault(Class<? extends Annotation> annotationClass, String element) {
        Method method = null;
        try {
            method = annotationClass.getMethod(element, (Class[]) null);
        } catch (NoSuchMethodException e) {
            return null;
        }
        return ((T) method.getDefaultValue());
    }

    public static String prepareJavaConventionName(String phrase) {
        String name = WordUtils.capitalizeFully(phrase).replaceAll(WHITE_SPACE, EMPTY_STRING);
        return WordUtils.uncapitalize(name);
    }

    public static List<String> prepareFieldNames(boolean skipFirstLine, String separator, String line) {
        final String defaultFieldNamePrefix = "field";
        Stream<String> stream = Arrays.stream(line.split(separator));
        if (skipFirstLine) {
            return stream
                    .map(Utils::prepareJavaConventionName)
                    .collect(Collectors.toList());
        } else {
            return LongStream
                    .rangeClosed(1, stream.count())
                    .mapToObj(i -> String.format("%s%s", defaultFieldNamePrefix, i))
                    .collect(Collectors.toList());
        }
    }

    public static void prepareJavaFile(String packageName, TypeSpec classNode, String path) throws IOException {
        JavaFile javaFile = JavaFile.builder(packageName, classNode).skipJavaLangImports(true).build();
        javaFile.writeTo(Paths.get(path));
    }

}
