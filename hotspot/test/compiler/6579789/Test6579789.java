/**
 * @test
 * @bug 6579789
 * @summary Internal error "c1_LinearScan.cpp:1429 Error: assert(false,"")" in debuggee with fastdebug VM
 * @run main/othervm -Xcomp -XX:UseSSE=0 -XX:CompileOnly=Test6579789.bug Test6579789
 */

public class Test6579789 {
    public static void main(String[] args) {
        bug(4);
    }
    public static void bug(int n) {
        float f = 1;
        int i = 1;
        try {
            int x = 1 / n; // instruction that can trap
            f = 2;
            i = 2;
            int y = 2 / n; // instruction that can trap
        } catch (Exception ex) {
            f++;
            i++;
        }
    }
}
