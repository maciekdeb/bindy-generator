package pl.lodz.p.bindy_generator.params;

import com.beust.jcommander.Parameter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maciek on 10/01/16.
 */
public class CsvRecordParams implements AnnotationsParams {

    @Parameter(required = true, names = {"-s", "--separator"}, description = "Skip the first line of CSV.")
    public String separator;

    @Parameter(names = {"-sfl", "--skipFirstLine"}, description = "Skip the first line of CSV.")
    public boolean skipFirstLine;

    @Parameter(names = "-crlf")
    public String crlf;

    @Parameter(names = "-generateHeaderColumns")
    public boolean generateHeaderColumns;

    @Parameter(names = "-autospanLine")
    public boolean autospanLine;

    @Parameter(names = "-isOrdered")
    public boolean isOrdered;

    @Parameter(names = "-quote")
    public String quote;

    @Parameter(names = "-quoting")
    public boolean quoting;

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
        result.put("quoting", quoting);
        return result;
    }
}
