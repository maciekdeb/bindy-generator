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

    @Parameter(names = "-c", description = "")
    public String crlf = Utils.<String>getAnnotationDefault(CLASS, "crlf");

    @Override
    public Map<String, Object> getAnnotationsMembers() {
        Map<String, Object> result = new HashMap<>();
        result.put("crlf", crlf);
        return result;
    }
}
