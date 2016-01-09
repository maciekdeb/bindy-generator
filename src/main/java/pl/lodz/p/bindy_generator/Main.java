package pl.lodz.p.bindy_generator;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

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
        Commands jc = new Commands(args);

        Path path = Paths.get(jc.getFileName());
        String firstLine = Files.lines(path).findFirst().get();


        List<String> fieldsNames = Utils.prepareFieldNames(jc.skipFirstLine, jc.separator, firstLine);

        TypeSpec classNode = new CsvModelBuilder(jc.getClassName())
                .withField(String.class, "fieldMy")
                .build();

        Utils.prepareJavaFile(jc.getPackageName(), classNode, ".");

        if (false) {
            jc.getJCommander().usage();
        }
    }



}
