package pl.lodz.p.params.type;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import pl.lodz.p.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maciek on 10/01/16.
 */
@Parameters(commandDescription = "Generates fixed length based domain class")
public class FixedLengthClassParams extends AnnotationsClassParams {

    @Parameter(names = "--crlf", description = "")
    public String crlf = Utils.<String>getAnnotationDefault(getType(), "crlf");

    @Parameter(names = "--paddingChar", description = "")
    public Character paddingChar = Utils.<Character>getAnnotationDefault(getType(), "paddingChar");

    @Parameter(names = "--length", description = "")
    public Integer length = Utils.<Integer>getAnnotationDefault(getType(), "length");

    @Parameter(names = "--hasHeader", description = "")
    public Boolean hasHeader = Utils.<Boolean>getAnnotationDefault(getType(), "hasHeader");

    @Parameter(names = "--hasFooter", description = "")
    public Boolean hasFooter = Utils.<Boolean>getAnnotationDefault(getType(), "hasFooter");

    @Parameter(names = "--skipHeader", description = "")
    public Boolean skipHeader = Utils.<Boolean>getAnnotationDefault(getType(), "skipHeader");

    @Parameter(names = "--skipFooter", description = "")
    public Boolean skipFooter = Utils.<Boolean>getAnnotationDefault(getType(), "skipFooter");

    @Parameter(names = "--isHeader", description = "")
    public Boolean isHeader = Utils.<Boolean>getAnnotationDefault(getType(), "isHeader");

    @Parameter(names = "--isFooter", description = "")
    public Boolean isFooter = Utils.<Boolean>getAnnotationDefault(getType(), "isFooter");

    @Parameter(names = "--ignoreTrailingChars", description = "")
    public Boolean ignoreTrailingChars = Utils.<Boolean>getAnnotationDefault(getType(), "ignoreTrailingChars");

    @Override
    public void initializeFields() {
        result.put("crlf", crlf);
        result.put("paddingChar", paddingChar);
        result.put("length", length);
        result.put("hasHeader", hasHeader);
        result.put("hasFooter", hasFooter);
        result.put("skipHeader", skipHeader);
        result.put("skipFooter", skipFooter);
        result.put("isHeader", isHeader);
        result.put("isFooter", isFooter);
        result.put("ignoreTrailingChars", ignoreTrailingChars);
    }

    @Override
    Class getType() {
        return FixedLengthRecord.class;
    }

    @Override
    List<String> getRequiredFields() {
        List<String> requiredFields = new ArrayList<>();
        requiredFields.add("paddingChar");
        requiredFields.add("length");
        return requiredFields;
    }

}
