import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.junit.Test;

import javax.lang.model.element.Modifier;

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
}
