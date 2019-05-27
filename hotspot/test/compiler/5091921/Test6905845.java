/**
 * @test
 * @bug 6905845
 * @summary Server VM improperly optimizing away loop.
 *
 * @run main/timeout=480 Test6905845
 */

public class Test6905845 {

   public static void main(String[] args){
      for (int asdf = 0; asdf < 5; asdf++){
         //test block
         {
            StringBuilder strBuf1 = new StringBuilder(65);
            long          start   = System.currentTimeMillis();
            int           count   = 0;

            for (int i = Integer.MIN_VALUE; i < (Integer.MAX_VALUE - 80); i += 79){
               strBuf1.append(i);
               count++;
               strBuf1.delete(0, 65);
            }

            System.out.println(count);
            if (count != 54366674) {
              System.out.println("wrong count: " + count +", should be 54366674");
              System.exit(97);
            }
         }
         //test block
         {
            StringBuilder strBuf1 = new StringBuilder(65);
            long          start   = System.currentTimeMillis();
            int           count   = 0;

            for (int i = Integer.MIN_VALUE; i < (Integer.MAX_VALUE - 80); i += 79){
               strBuf1.append(i);
               count++;
               strBuf1.delete(0, 65);
            }

            System.out.println(count);
            if (count != 54366674) {
              System.out.println("wrong count: " + count +", should be 54366674");
              System.exit(97);
            }
         }
      }
   }
}

