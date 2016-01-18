import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import pl.lodz.p.bindy_generator.util.InferenceUtils;
import pl.lodz.p.bindy_generator.util.Utils;

import javax.lang.model.element.Modifier;
import java.util.Arrays;

/**
 * Created by maciekdeb on 2016-01-04.
 */
public class JavaPoetSampleTest {

    @Test
    public void testHelloWorldClass() throws Exception {

        MethodSpec main = MethodSpec.methodBuilder("main").build();

        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)
                .build();

        System.out.println(helloWorld);
    }

    @Test
    public void testMethodGetter() {

        String fieldName = "clientId";
        Class type = String.class;

        MethodSpec method = MethodSpec
                .methodBuilder("get" + StringUtils.capitalize(fieldName))
                .addModifiers(Modifier.PUBLIC)
                .returns(type)
                .addStatement("return $N", fieldName)
                .build();

        System.out.println(method);
    }

    @Test
    public void testMethodSetter() {

        String fieldName = "clientId";
        Class type = String.class;

        MethodSpec method = MethodSpec
                .methodBuilder("set" + StringUtils.capitalize(fieldName))
                .addModifiers(Modifier.PUBLIC)
                .addParameter(type, fieldName)
                .addStatement("this.$N = $N", fieldName, fieldName)
                .build();

        System.out.println(method);
    }
}
