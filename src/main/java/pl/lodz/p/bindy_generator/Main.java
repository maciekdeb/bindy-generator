package pl.lodz.p.bindy_generator;

import com.squareup.javapoet.TypeSpec;
import pl.lodz.p.bindy_generator.builder.CsvModelBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by maciek on 04/01/16.
 */
public class Main {

    public static final Config CONFIG = Config.getInstance();

    public static void main(String[] args) throws Exception {
        Commands jc = null;
        try {
            jc = new Commands(args);

            Path path = Paths.get(jc.getFileName());
            String firstLine = Files.lines(path).findFirst().get();

            List<String> fieldsNames = Utils.prepareFieldNames(jc.skipFirstLine, jc.separator, firstLine);

            TypeSpec classNode = new CsvModelBuilder(jc)
                    .withField(String.class, "fieldMy")
                    .build();

            Utils.prepareJavaFile(jc.getPackageName(), classNode, ".");
        } catch (Exception e) {
            jc.getJCommander().usage();
        }
    }

}
