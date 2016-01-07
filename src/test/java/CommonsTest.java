import junit.framework.Assert;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.junit.Test;
import pl.lodz.p.bindy_generator.Utils;

/**
 * Created by maciek on 06/01/16.
 */
public class CommonsTest {

    @Test
    public void testDefualtAnnotationValue() throws Exception {
        Assert.assertFalse((Boolean) Utils.getAnnotationDefault(CsvRecord.class, "skipFirstLine"));
    }

    @Test
    public void testCommons() {
        String origal = "jedeN dWA TRZY";
        String expected = "jedenDwaTrzy";
        Assert.assertEquals(expected, Utils.prepareJavaValidMethodName(origal));
    }

    @Test
    public void testFieldGeneratorWithLineSkipped() throws Exception {
        System.out.println(Utils.prepareFieldNames(true, ",", "12, 1.2, shoe"));
    }

    @Test
    public void testFieldGeneratorWithLines() throws Exception {
        System.out.println(Utils.prepareFieldNames(false, ",", "id, price, product name"));
    }

}
