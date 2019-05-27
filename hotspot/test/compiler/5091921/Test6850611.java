/**
 * @test
 * @bug 6850611
 * @summary int / long arithmetic seems to be broken in 1.6.0_14 HotSpot Server VM (Win XP)
 *
 * @run main/timeout=480 Test6850611
 */

public class Test6850611 {

    public static void main(String[] args) {
        test();
    }

    private static void test() {
        for (int j = 0; j < 5; ++j) {
            long x = 0;
            for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; ++i) {
                x += i;
            }
            System.out.println("sum: " + x);
            if (x != -4294967295l) {
                System.out.println("FAILED");
                System.exit(97);
            }
        }
    }
}

