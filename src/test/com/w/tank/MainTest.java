package test.com.w.tank;

import org.junit.Assert;
import org.junit.Test;

public class MainTest {

    @Test
    public void test_01(){
        try {

            Assert.assertNotNull(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
