package pl.lodz.p.bindy_generator.params;

import com.beust.jcommander.Parameter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maciek on 10/01/16.
 */
public class CsvRecordClassParams implements AnnotationsClassParams {

    @Parameter(required = true, names = {"-s", "--separator"}, description = "field separator")
    public String separator;

    @Parameter(names = {"-x", "--skipFirstLine"}, description = "skip the first line of CSV")
    public boolean skipFirstLine;

    @Parameter(names = {"-l", "--crlf"}, description = "crlf")
    public String crlf;

    @Parameter(names = {"-g", "--generateHeaderColumns"})
    public boolean generateHeaderColumns;

    @Parameter(names = {"-a", "--autospanLine"})
    public boolean autospanLine;

    @Parameter(names = {"-o", "--isOrdered"})
    public boolean isOrdered;

    @Parameter(names = {"-q", "--quote"})
    public String quote;

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
