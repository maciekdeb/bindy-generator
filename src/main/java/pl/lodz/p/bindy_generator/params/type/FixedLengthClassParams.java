package pl.lodz.p.bindy_generator.params.type;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import pl.lodz.p.bindy_generator.util.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maciek on 10/01/16.
 */
@Parameters(commandDescription = "Generates fixed length based domain class")
public class FixedLengthClassParams implements AnnotationsClassParams {

    private static final Class CLASS = FixedLengthRecord.class;

    @Parameter(names = "--crlf", description = "")
    public String crlf = Utils.<String>getAnnotationDefault(CLASS, "crlf");

    @Parameter(names = "--paddingChar", description = "")
    public Character paddingChar = Utils.<Character>getAnnotationDefault(CLASS, "paddingChar");

    @Parameter(names = "--length", description = "")
    public Integer length = Utils.<Integer>getAnnotationDefault(CLASS, "length");

    @Parameter(names = "--hasHeader", description = "")
    public Boolean hasHeader = Utils.<Boolean>getAnnotationDefault(CLASS, "hasHeader");

    @Parameter(names = "--hasFooter", description = "")
    public Boolean hasFooter = Utils.<Boolean>getAnnotationDefault(CLASS, "hasFooter");

    @Parameter(names = "--skipHeader", description = "")
    public Boolean skipHeader = Utils.<Boolean>getAnnotationDefault(CLASS, "skipHeader");

    @Parameter(names = "--skipFooter", description = "")
    public Boolean skipFooter = Utils.<Boolean>getAnnotationDefault(CLASS, "skipFooter");

    @Parameter(names = "--isHeader", description = "")
    public Boolean isHeader = Utils.<Boolean>getAnnotationDefault(CLASS, "isHeader");

    @Parameter(names = "--isFooter", description = "")
    public Boolean isFooter = Utils.<Boolean>getAnnotationDefault(CLASS, "isFooter");

    @Parameter(names = "--ignoreTrailingChars", description = "")
    public Boolean ignoreTrailingChars = Utils.<Boolean>getAnnotationDefault(CLASS, "ignoreTrailingChars");

    @Override
    public Map<String, Object> getAnnotationsMembers() {
        Map<String, Object> result = new HashMap<>();
        result.put("crlf", crlf);
        return result;
    }
}
