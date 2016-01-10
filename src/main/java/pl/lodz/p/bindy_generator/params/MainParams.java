package pl.lodz.p.bindy_generator.params;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParametersDelegate;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import pl.lodz.p.bindy_generator.params.AnnotationsParams;
import pl.lodz.p.bindy_generator.params.CsvRecordParams;

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

    @ParametersDelegate
    public CsvRecordParams csvRecordParams = new CsvRecordParams();

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
        AnnotationsParams annotationsParams = null;
        if (aClass.equals(CsvRecord.class)) {
            annotationsParams = csvRecordParams;
        }
        return annotationsParams.getAnnotationsMembers();
    }

    public JCommander getJCommander() {
        return jCommander;
    }
}
