import org.junit.Test;

import java.io.File;

/**
 * @author CasonCai
 * @since 2021/4/20 下午4:01
 **/
public class TestObject {


    @Test
    public void testDir() {
        String dir = System.getProperty("user.dir");
        System.out.println(dir);
        System.out.println(new File("./").getAbsolutePath());
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        System.out.println(new File("./").getAbsolutePath());

    }
}
