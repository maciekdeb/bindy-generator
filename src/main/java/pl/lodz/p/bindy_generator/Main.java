package pl.lodz.p.bindy_generator;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.bindy_generator.builder.JavaDomainBuilder;
import pl.lodz.p.bindy_generator.params.MainParams;
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

    static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        MainParams jc = null;
        try {
            jc = new MainParams(args);

            Path path = Paths.get(jc.file);
            List<String> lines = Files.lines(path).collect(Collectors.toList());
            String firstLine = lines.get(0);

            JavaDomainBuilder classNode = null;
            String command = jc.getJCommander().getParsedCommand();
            if (MainParams.CSV_PARAM.equals(command)) {
                LOGGER.info("Started generating csv model");
                classNode = new JavaDomainBuilder(jc, CsvRecord.class);
                List<String> fieldsNames = Utils.prepareFieldNames(jc.csv.skipFirstLine, jc.csv.separator, firstLine);
                for (int i = 0; i < fieldsNames.size(); i++) {
                    Class type = InferenceUtils.inferFieldType(lines, jc.csv.skipFirstLine, jc.csv.separator, i);
                    classNode.withField(type, (i + 1), fieldsNames.get(i));
                }
            } else if (MainParams.FIXED_PARAM.equals(command)) {
                LOGGER.info("Started generating fixed length model");
                classNode = new JavaDomainBuilder(jc, FixedLengthRecord.class);

            }
            Utils.prepareJavaFile(jc.getPackageName(), classNode.build(), jc.path);
            LOGGER.info("New Java class " + jc.getClassNameWithPackage() + " file written in this directory " + jc.path);

        } catch (Exception e) {
            e.printStackTrace();
            jc.getJCommander().usage();
        }
    }

}
