import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomTest {
    @Test
    public void testRand() {
        Random r1 = new Random();
        Random r2 = new Random();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            r1.nextInt(9999999);
            r2.nextInt(9999999);
            r1.nextInt(9999999);
            r2.nextInt(9999999);
            r1.nextInt(9999999);
            r2.nextInt(9999999);
            r1.nextInt(9999999);
            r2.nextInt(9999999);
            // System.out.println("第" + i + "次:" + r1.nextInt(9999999) + "\t" + r2.nextInt(9999999));
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start) / 1000 + "秒");
    }

    @Test
    public void testThreadLocalRandom() {
        ThreadLocalRandom r1 = ThreadLocalRandom.current();
        ThreadLocalRandom r2 = ThreadLocalRandom.current();
        long start = System.currentTimeMillis();
        // 循环20亿次性能比较
        for (int i = 0; i < 2000000000; i++) {
            r1.nextInt(9999999);
            r2.nextInt(9999999);
            r1.nextInt(9999999);
            r2.nextInt(9999999);
            r1.nextInt(9999999);
            r2.nextInt(9999999);
            r1.nextInt(9999999);
            r2.nextInt(9999999);
            // System.out.println("第" + i + "次:" + r1.nextInt(9999999) + "\t" + r2.nextInt(9999999));
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时:" + (end - start) / 1000 + "秒");
    }
}
