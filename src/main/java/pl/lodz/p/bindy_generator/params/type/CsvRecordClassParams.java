package pl.lodz.p.bindy_generator.params.type;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import pl.lodz.p.bindy_generator.params.type.AnnotationsClassParams;
import pl.lodz.p.bindy_generator.util.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maciek on 10/01/16.
 */
@Parameters(commandDescription = "Generates csv based domain class")
public class CsvRecordClassParams implements AnnotationsClassParams {

    private static final Class CLASS = CsvRecord.class;

    @Parameter(names = {"-s", "--separator"}, description = "field separator")
    public String separator;

    @Parameter(names = {"-x", "--skipFirstLine"}, description = "skip the first line of CSV")
    public Boolean skipFirstLine = Utils.<Boolean>getAnnotationDefault(CLASS, "skipFirstLine");

    @Parameter(names = {"-l", "--crlf"}, description = "allow to define the carriage return character to use")
    public String crlf = Utils.<String>getAnnotationDefault(CLASS, "crlf");

    @Parameter(names = {"-g", "--generateHeaderColumns"}, description = "uses to generate the header columns of the CSV generates")
    public Boolean generateHeaderColumns = Utils.<Boolean>getAnnotationDefault(CLASS, "generateHeaderColumns");

    @Parameter(names = {"-a", "--autospanLine"}, description = "if enabled then the last column is auto spanned to end of line")
    public Boolean autospanLine = Utils.<Boolean>getAnnotationDefault(CLASS, "autospanLine");

    @Parameter(names = {"-o", "--isOrdered"}, description = "allow to change the order of the fields when CSV is generated")
    public Boolean isOrdered = Utils.<Boolean>getAnnotationDefault(CLASS, "isOrdered");

    @Parameter(names = {"-q", "--quote"}, description = "allow to specify a quote character of the fields when CSV is generated")
    public String quote = Utils.<String>getAnnotationDefault(CLASS, "quote");

    @Override
    public Map<String, Object> getAnnotationsMembers() {
        Map<String, Object> result = new HashMap<>();
        result.put("separator", separator);
        result.put("skipFirstLine", skipFirstLine);
        result.put("crlf", crlf);
        result.put("generateHeaderColumns", generateHeaderColumns);
        result.put("autospanLine", autospanLine);
        result.put("isOrdered", isOrdered);
        result.put("quote", quote);
        return result;
    }
}
