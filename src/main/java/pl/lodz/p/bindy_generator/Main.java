package pl.lodz.p.bindy_generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;

import javax.lang.model.element.Modifier;
import java.util.List;

/**
 * Created by maciek on 04/01/16.
 */
public class Main {

    public static final Config CONFIG = Config.getInstance();

    static class JCommanderParse {
        @Parameter(required = true, description = "parameters")
        public List<String> parameters;
    }

    public static void main(String[] args) throws Exception {
        JCommanderParse jc = new JCommanderParse();
        new JCommander(jc, args);

        String className = "Order";
        String packageName = "com.example.helloworld";

        FieldSpec field1 = FieldSpec.builder(String.class, "field1")
                .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
                .build();

        AnnotationSpec csvRecord = AnnotationSpec.builder(CsvRecord.class)
                .addMember("separator", "$S", ",")
                .build();

        TypeSpec classNode = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(csvRecord)
                .addField(field1)
                .addField(field1)
                .addJavadoc(generationMark())
                .build();

        JavaFile javaFile = JavaFile.builder(packageName, classNode)
                .build();

        javaFile.writeTo(System.out);
    }

    public static String generationMark() {
        return "Class automatically generated. Any change can be overwritten.\n"
                + "Model representation ready to use with Apache Camel Bindy.\n"
                + String.format("\n%s %s\n", CONFIG.getProperty("artifactId"), CONFIG.getProperty("version"));
    }


}
