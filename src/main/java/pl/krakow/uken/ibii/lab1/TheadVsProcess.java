package pl.ukrakow.ibii.lab1;

import java.lang.ProcessHandle;

public class TheadVsProcess {
    static int[] shared = new int[2];

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[2];

        for(int i = 0; i < 2; i++) {
            int number = i;
            threads[i] = new Thread(() -> {
                int internal = number * 10;
                shared[number] = internal + 1;
            });

            threads[i].start();
        }

        for(Thread t : threads) {
            t.join();
        }

        System.out.println("Heap: ");
        System.out.println("\tshared[0]: " + shared[0]);
        System.out.println("\tshared[1]: " + shared[1]);

        System.out.println("PID: " + ProcessHandle.current().pid());
    }

}
