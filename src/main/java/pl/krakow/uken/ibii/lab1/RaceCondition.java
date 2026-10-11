package pl.ukrakow.ibii.lab1;

import java.util.concurrent.atomic.AtomicInteger;

public class RaceCondition {
    static final int NUM_THREADS = 4;
    static final int N = 250_000;

    static int number;
    static volatile int volatile_number;
    static int protected_number;

    static final Object LOCK = new Object();
    static final AtomicInteger atom = new AtomicInteger();

    static void run(Runnable runnable) throws Exception {
        Thread[] t = new Thread[NUM_THREADS];
        for(int i = 0; i < NUM_THREADS; i++) {
            t[i] = new Thread(() -> {
                for (int k = 0; k < N; k++) {
                    runnable.run();
                }
            });
            t[i].start();
        }

        for(Thread w : t) {
            w.join();
        }
    }

    public static void main(String[] args) throws Exception {
        run(() -> number++);
        run(() -> volatile_number++);
        run(() -> {
            synchronized (LOCK) { protected_number++; }
        });

        run(atom::incrementAndGet);

        System.out.println();

        System.out.println("int           = " + number);
        System.out.println("volatile int  = " + volatile_number);
        System.out.println("synchronized  = " + protected_number);
        System.out.println("AtomicInteger = " + atom.get());
    }
}
