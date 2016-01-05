import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.util.List;

/**
 * Created by maciek on 04/01/16.
 */
public class Main {

    static class JCommanderParse {
        @Parameter(required = true, description = "parameters")
        public List<String> parameters;
    }

    public static void main(String[] args) {
        JCommanderParse jc = new JCommanderParse();
        new JCommander(jc, args);

        System.out.println(jc.parameters);

    }

}
