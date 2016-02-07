import junit.framework.Assert;
import org.junit.Test;
import pl.lodz.p.util.InferenceUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maciek on 06/01/16.
 */
public class InferenceUtilsTest {

    @Test
    public void testInteger(){
        Assert.assertTrue(InferenceUtils.isInteger("+3".trim()));
        Assert.assertTrue(InferenceUtils.isInteger("-3".trim()));
        Assert.assertTrue(InferenceUtils.isInteger("3".trim()));
        Assert.assertTrue(InferenceUtils.isInteger(" 3".trim()));
        Assert.assertTrue(InferenceUtils.isInteger("312 ".trim()));
        Assert.assertTrue(InferenceUtils.isInteger("0312 ".trim()));
        Assert.assertFalse(InferenceUtils.isInteger("0312.0 ".trim()));
        Assert.assertFalse(InferenceUtils.isInteger("test ".trim()));
        Assert.assertFalse(InferenceUtils.isInteger("- 3".trim()));
        Assert.assertFalse(InferenceUtils.isInteger("+-2".trim()));
    }

    @Test
    public void testInferFieldTypeEveryString() {
        String line1 = "a,a,a,a";
        String line2 = "a,a,a,a";
        List<String> list = new ArrayList<>();
        list.add(line1);
        list.add(line2);

        Class type = InferenceUtils.inferFieldType(list,false,  ",", 2);
        Assert.assertEquals(String.class, type);
    }

    @Test
    public void testInferFieldTypeAnyString() {
        String line1 = "a,a,,a";
        String line2 = "a,a,1,a";
        List<String> list = new ArrayList<>();
        list.add(line1);
        list.add(line2);

        Class type = InferenceUtils.inferFieldType(list, false, ",", 2);
        Assert.assertEquals(String.class, type);
    }

    @Test
    public void testInferFieldTypeEveryInteger() {
        String line1 = "a,a,23,a";
        String line2 = "a,a,1,a";
        List<String> list = new ArrayList<>();
        list.add(line1);
        list.add(line2);

        Class type = InferenceUtils.inferFieldType(list, false, ",", 2);
        Assert.assertEquals(Integer.class, type);
    }

}
