package pl.lodz.p.bindy_generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by maciek on 06/01/16.
 */
public class Commands {

    private JCommander jCommander;

    @Parameter(required = true, description = "parameters")
    private List<String> parameters;

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

    public Commands(String[] arguments) {
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

    public Map<String, Object> getAnnotationsMembersCsvRecord(){
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

    public JCommander getJCommander() {
        return jCommander;
    }
}
