package net.jfdf.test.fib;

import net.jfdf.compiler.annotation.NoConstructors;

@NoConstructors
public class FibonacciSequence {
    public static void printFibonacciN(int n) {
        int a = 0;
        int b = 1;

        if(n >= 1) System.out.println(a);
        if(n >= 2) System.out.println(b);

        for (int i = 3; i <= n; i++) {
            int oldA = a;
            a = b;

            b = oldA + b;
            System.out.println(b);
        }
    }
}
