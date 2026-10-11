package pl.ukrakow.ibii.lab1;

public class CreatingThreads {
    static class MyThread extends Thread {
        @Override
        public void run() {
            log("Extends thread!");
        }
    }

    static class MyTask implements Runnable {
        @Override
        public void run() {
            log("implements Runnable!");
        }
    }

    static void log(String s) {
        System.out.println(s + " -> " + Thread.currentThread());
    }

    public static void main(String[] args) throws Exception {
        Thread a = new MyThread();
        Thread b = new Thread(new MyTask(), "zadanie-b");
        Thread c = new Thread(() -> log("lambda"), "lambda-c");

        a.start();
        b.start();
        c.start();

        a.join();
        b.join();
        c.join();

        // PUŁAPKA
        new Thread(() -> log("run() zamiast start()")).run();


        // MOŻE NIE ZADZIAŁAĆ NA STARSZEJ WERSJI
//        try(ExecutorService pool = Executors.newFixedThreadPool(2)) {
//            Callable<Integer> zad = () -> 6 * 7;
//            Future<Integer> future = pool.submit(zad);
//            System.out.println("Callabe/Future: " + future.get());
//        }
    }
}
