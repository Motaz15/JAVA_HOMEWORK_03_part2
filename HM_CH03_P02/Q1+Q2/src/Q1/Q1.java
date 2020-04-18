
package Q1;

import java.util.function.Consumer;
import java.util.function.IntConsumer;


public class ch03_P02Q1 {

    public static void main(String[] args) {

        // Q1/A

        Consumer<Integer> con1
                = (num) -> {
                    System.out.printf("%d ", num);
                };
        con1.accept(10);


        //Q1/B
        Operation<String> o1 = (String::toUpperCase);
        System.out.println(o1.apply("motaz"));

        //Q1/C
        
        welcome s = () -> System.out.println("WELCOME TO Lambdas");
        s.apply();


        //Q1/D
        arth cube = (num1) -> num1*num1*num1;
        System.out.println(cube.apply(3));
    }


    private interface Operation<String> {

        String apply(String x);
    }
    
    interface welcome {
    void apply();
    }

    interface arth<Integer>{
     int apply(int x);
    }
}
