import junit.framework.Assert;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.junit.Test;
import pl.lodz.p.bindy_generator.Utils;

/**
 * Created by maciek on 06/01/16.
 */
public class CamelBindyTest {

    @Test
    public void testDefualtAnnotationValue() throws Exception {
        Assert.assertFalse((Boolean) Utils.getAnnotationDefault(CsvRecord.class, "skipFirstLine"));
    }

}
