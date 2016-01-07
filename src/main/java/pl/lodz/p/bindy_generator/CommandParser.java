package pl.lodz.p.bindy_generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;

import java.util.List;

/**
 * Created by maciek on 06/01/16.
 */
public class CommandParser {

    @Parameter(required = true, description = "parameters")
    private List<String> parameters;

    @Parameter(required = true, names = {"-s", "--separator"}, description = "Skip the first line of CSV.")
    public String separator;

    @Parameter(names = {"-sfl", "--skipFirstLine"}, description = "Skip the first line of CSV.")
    public boolean skipFirstLine;

    private JCommander jCommander;

    public CommandParser(String[] arguments) {
        jCommander = new JCommander(this, arguments);
        jCommander.setProgramName("bindy-generator");
        try {
            skipFirstLine = Utils.getAnnotationDefault(CsvRecord.class, "skipFirstLine");
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public JCommander getJCommander() {
        return jCommander;
    }
}
