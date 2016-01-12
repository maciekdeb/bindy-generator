package pl.lodz.p.bindy_generator.params;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParametersDelegate;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by maciek on 06/01/16.
 */
public class MainParams {

    private JCommander jCommander;

    @Parameter(required = true, description = "parameters")
    private List<String> parameters;

    /**
     * Includes parameters for CsvRecord annotation like separator, skipFirstLine etc.
     */
    @ParametersDelegate
    public CsvRecordClassParams csvRecordParams = new CsvRecordClassParams();

    /**
     * Represents dynamic sets describing fields parameters (-f1=name(field1),pos(2))
     */
    @DynamicParameter(names = "-f")
    private Map<Integer, String> fields = new HashMap<>();

    public MainParams(String[] arguments) {
        jCommander = new JCommander(this, arguments);
        jCommander.setProgramName("bindy-generator");
    }

    public String getFileName() {
        if (parameters != null) {
            String fileName = parameters.get(0);
            if (fileName != null) {
                return fileName;
            }
        }
        return "";
    }

    public String getClassNameWithPackage() {
        if (parameters != null) {
            String className = parameters.get(1);
            if (className != null) {
                return className;
            }
        }
        return "";
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
            return csvRecordParams.getAnnotationsMembers();
        } else return null;
    }

    public Map<String, Object> getAnnotationMembers(Class aClass, int pos) {
        if (DataField.class.equals(aClass)) {
            return new DataFieldClassParams(fields).getAnnotationsMembers(pos);
        } else return null;
    }

    public JCommander getJCommander() {
        return jCommander;
    }
}
