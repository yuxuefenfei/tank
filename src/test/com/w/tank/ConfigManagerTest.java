package test.com.w.tank;

import com.w.tank.ConfigManager;
import org.junit.Test;

public class ConfigManagerTest {

    @Test
    public void test_01(){
        String tankNum = ConfigManager.get("tankNum");
        System.out.println(tankNum);
    }
}
