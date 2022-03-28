package net.jfdf.test.fib;

import net.jfdf.compiler.annotation.NoConstructors;
import net.jfdf.jfdf.blocks.PlayerEventBlock;
import net.jfdf.jfdf.mangement.PlayerEvent;

@NoConstructors
public class FibonacciListener {
    @PlayerEvent(eventType = PlayerEventBlock.Event.JOIN)
    private static void onJoin() {
        FibonacciSequence.printFibonacciN(10);
    }
}