package pl.lodz.p.bindy_generator;

import com.squareup.javapoet.TypeSpec;
import pl.lodz.p.bindy_generator.builder.CsvModelBuilder;
import pl.lodz.p.bindy_generator.params.MainParams;
import pl.lodz.p.bindy_generator.util.Config;
import pl.lodz.p.bindy_generator.util.InferenceUtils;
import pl.lodz.p.bindy_generator.util.Utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by maciek on 04/01/16.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        MainParams jc = null;
        try {
            jc = new MainParams(args);

            Path path = Paths.get(jc.getFileName());
            List<String> lines = Files.lines(path).collect(Collectors.toList());
            String firstLine = lines.get(0);

            CsvModelBuilder classNode = new CsvModelBuilder(jc);

            List<String> fieldsNames = Utils.prepareFieldNames(jc.csvRecordParams.skipFirstLine, jc.csvRecordParams.separator, firstLine);
            for (int i = 0; i < fieldsNames.size(); i++) {
                Class type = InferenceUtils.inferFieldType(lines, jc.csvRecordParams.skipFirstLine, jc.csvRecordParams.separator, i);
                classNode.withField(type, (i+1), fieldsNames.get(i));
            }

            Utils.prepareJavaFile(jc.getPackageName(), classNode.build(), ".");
        } catch (Exception e) {
            e.printStackTrace();
            jc.getJCommander().usage();
        }
    }

}
