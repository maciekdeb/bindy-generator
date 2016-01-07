package pl.lodz.p.bindy_generator;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maciek on 04/01/16.
 */
public class Main {

    public static final Config CONFIG = Config.getInstance();

    public static void main(String[] args) throws Exception {
        CommandParser jc = new CommandParser(args);

        Path path = Paths.get(jc.getFileName());
        String firstLine = Files.lines(path).findFirst().get();
        List<String> fieldsNames = Utils.prepareFieldNames(jc.skipFirstLine, jc.separator, firstLine);
        TypeSpec classNode = prepareClassNode(jc.getClassName(), prepareFields(fieldsNames));
        prepareJavaFile(jc.getPackageName(), classNode);

        if (false) {
            jc.getJCommander().usage();
        }
    }

    public static List<FieldSpec> prepareFields(List<String> fieldsNames) {
        List<FieldSpec> fieldSpecs = new ArrayList<>();
        for (String name : fieldsNames) {
            FieldSpec field = FieldSpec.builder(String.class, name)
                    .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
                    .build();
            fieldSpecs.add(field);
        }
        return fieldSpecs;
    }

    public static TypeSpec prepareClassNode(String className, List<FieldSpec> fields) {

        AnnotationSpec csvRecord = AnnotationSpec.builder(CsvRecord.class)
                .addMember("separator", "$S", ",")
                .build();

        TypeSpec.Builder classNode = TypeSpec.classBuilder(className)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(csvRecord)
                .addFields(fields)
                .addJavadoc(CONFIG.generationMark());

        return classNode.build();
    }

    private static void prepareJavaFile(String packageName, TypeSpec classNode) throws IOException {
        JavaFile javaFile = JavaFile.builder(packageName, classNode).skipJavaLangImports(true).build();
        javaFile.writeTo(System.out);
    }


}
