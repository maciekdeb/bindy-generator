import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.internal.Lists;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.junit.Assert;
import org.junit.Test;

import javax.lang.model.element.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by maciekdeb on 2016-01-04.
 */
public class JCommanderTest {

    @Test
    public void testJCommander() throws Exception {
        JCommanderComp jc = new JCommanderComp();
        String[] argv = {"-log", "2", "-groups", "unit1,unit2,unit3",
                "-debug", "-Doption=value", "a", "b", "c"};

        new JCommander(jc, argv);

        Assert.assertEquals(2, jc.verbose.intValue());
        Assert.assertEquals("unit1,unit2,unit3", jc.groups);
        Assert.assertEquals(true, jc.debug);
        Assert.assertEquals("value", jc.dynamicParams.get("option"));
        Assert.assertEquals(Arrays.asList("a", "b", "c"), jc.parameters);
    }
}

class JCommanderComp {
    @Parameter
    public List<String> parameters = Lists.newArrayList();

    @Parameter(names = { "-log", "-verbose" }, description = "Level of verbosity")
    public Integer verbose = 1;

    @Parameter(names = "-groups", description = "Comma-separated list of group names to be run")
    public String groups;

    @Parameter(names = "-debug", description = "Debug mode")
    public boolean debug = false;

    @DynamicParameter(names = "-D", description = "Dynamic parameters go here")
    public Map<String, String> dynamicParams = new HashMap<String, String>();
}