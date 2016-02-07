package pl.lodz.p.params.type;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import pl.lodz.p.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maciek on 10/01/16.
 */
@Parameters(commandDescription = "Generates csv based domain class")
public class CsvRecordClassParams extends AnnotationsClassParams {

    @Parameter(names = {"-s", "--separator"}, description = "field separator")
    public String separator;

    @Parameter(names = {"-x", "--skipFirstLine"}, description = "skip the first line of CSV")
    public Boolean skipFirstLine = Utils.<Boolean>getAnnotationDefault(getType(), "skipFirstLine");

    @Parameter(names = {"-l", "--crlf"}, description = "allow to define the carriage return character to use")
    public String crlf = Utils.<String>getAnnotationDefault(getType(), "crlf");

    @Parameter(names = {"-g", "--generateHeaderColumns"}, description = "uses to generate the header columns of the CSV generates")
    public Boolean generateHeaderColumns = Utils.<Boolean>getAnnotationDefault(getType(), "generateHeaderColumns");

    @Parameter(names = {"-a", "--autospanLine"}, description = "if enabled then the last column is auto spanned to end of line")
    public Boolean autospanLine = Utils.<Boolean>getAnnotationDefault(getType(), "autospanLine");

    @Parameter(names = {"-o", "--isOrdered"}, description = "allow to change the order of the fields when CSV is generated")
    public Boolean isOrdered = Utils.<Boolean>getAnnotationDefault(getType(), "isOrdered");

    @Parameter(names = {"-q", "--quote"}, description = "allow to specify a quote character of the fields when CSV is generated")
    public String quote = Utils.<String>getAnnotationDefault(getType(), "quote");

    @Override
    void initializeFields() {
        result.put("separator", separator);
        result.put("skipFirstLine", skipFirstLine);
        result.put("crlf", crlf);
        result.put("generateHeaderColumns", generateHeaderColumns);
        result.put("autospanLine", autospanLine);
        result.put("isOrdered", isOrdered);
        result.put("quote", quote);
    }

    @Override
    Class getType() {
        return CsvRecord.class;
    }

    @Override
    List<String> getRequiredFields() {
        List<String> requiredFields = new ArrayList<>();
        requiredFields.add("separator");
        return requiredFields;

    }


}
