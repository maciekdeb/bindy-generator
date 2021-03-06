package pl.lodz.p.params;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.FixedLengthRecord;
import pl.lodz.p.params.property.DataFieldPropertyParams;
import pl.lodz.p.params.type.CsvRecordClassParams;
import pl.lodz.p.params.type.FixedLengthClassParams;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by maciek on 06/01/16.
 */
public class MainParams {

    private JCommander jCommander;

    @Parameter(required = true, names = "-f", description = "FileName")
    public String file;

    @Parameter(required = true, names = "-c", description = "ClassNameWithPackage")
    private String className;

    @Parameter(names = {"-p", "--path"}, description = "path for generated java class")
    public String path = ".";

    public static final String CSV_PARAM = "csv";

    /**
     * Includes parameters for CsvRecord annotation like separator, skipFirstLine etc.
     */
    public CsvRecordClassParams csv = new CsvRecordClassParams();

    public static final String FIXED_PARAM = "fixed";

    public FixedLengthClassParams fixed = new FixedLengthClassParams();

    /**
     * Represents dynamic sets describing fields parameters (-f1=name(field1),pos(2))
     */
    @DynamicParameter(names = "-field", description = "-field<number>=<options> ex. -field1=pos(2)")
    private Map<String, String> fields = new HashMap<>();

    public MainParams(String[] arguments) {
        jCommander = new JCommander(this);
        jCommander.setProgramName("bindy-generator");
        jCommander.addCommand(FIXED_PARAM, this.fixed);
        jCommander.addCommand(CSV_PARAM, this.csv);
        jCommander.parse(arguments);
    }

    public String getClassNameWithPackage() {
        return className;
    }

    public String getPackageName() {
        String packageName = getClassNameWithPackage();
        if (packageName != null) {
            int lastDot = packageName.lastIndexOf('.');
            return packageName.substring(0, lastDot);
        }
        return "";
    }

    public String getClassName() {
        String className = getClassNameWithPackage();
        if (className != null) {
            int lastDot = className.lastIndexOf('.');
            return className.substring(lastDot + 1);
        }
        return "";
    }

    public Map<String, Object> getAnnotationMembers(Class aClass) {
        if (CsvRecord.class.equals(aClass)) {
            return csv.getAnnotationsMembers();
        } else if (FixedLengthRecord.class.equals(aClass)) {
            return fixed.getAnnotationsMembers();
        } else return null;
    }

    public Map<String, Object> getAnnotationMembers(Class aClass, int pos) {
        if (DataField.class.equals(aClass)) {
            return new DataFieldPropertyParams().getAnnotationsMembers(fields.get(String.valueOf(pos)), pos);
        } else return null;
    }

    public Set<String> getFields(){
        return fields.keySet();
    }

    public JCommander getJCommander() {
        return jCommander;
    }
}
