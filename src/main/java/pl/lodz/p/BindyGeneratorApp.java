package pl.lodz.p;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import org.apache.camel.dataformat.bindy.format.LongFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.builder.JavaDomainBuilder;
import pl.lodz.p.params.MainParams;
import pl.lodz.p.util.InferenceUtils;
import pl.lodz.p.util.Utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by maciek on 04/01/16.
 */
public class BindyGeneratorApp {

    static Logger LOGGER = LoggerFactory.getLogger(BindyGeneratorApp.class);

    public static void main(String[] args) throws Exception {
        generate(args);
    }

    public static void generate(String[] args) throws Exception {
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
                List<String> fieldsNames = Utils.prepareFieldNamesCsv(jc.csv.skipFirstLine, jc.csv.separator, firstLine);
                for (int i = 0; i < fieldsNames.size(); i++) {
                    Class type = InferenceUtils.inferFieldType(lines, jc.csv.skipFirstLine, jc.csv.separator, i);
                    classNode.withField(type, (i + 1), fieldsNames.get(i));
                }
            } else if (MainParams.FIXED_PARAM.equals(command)) {
                LOGGER.info("Started generating fixed length model");
                classNode = new JavaDomainBuilder(jc, FixedLengthRecord.class);
                for (Long fieldEnumerator : jc.getFields().stream().map(Long::parseLong).collect(Collectors.toList())) {
                    classNode.withField(String.class, fieldEnumerator.intValue(), Utils.prepareFieldName(fieldEnumerator));
                }
            }
            Utils.prepareJavaFile(jc.getPackageName(), classNode.build(), jc.path);
            LOGGER.info("New Java class " + jc.getClassNameWithPackage() + " file written in this directory " + jc.path);

        } catch (Exception e) {
            e.printStackTrace();
            jc.getJCommander().usage();
        }
    }

}
