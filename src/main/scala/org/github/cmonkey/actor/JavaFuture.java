package org.github.cmonkey.actor;

import java.util.concurrent.*;

public class JavaFuture {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<Integer> f = executorService.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                System.out.println("execute call");

                TimeUnit.SECONDS.sleep(1);

                return 5;
            }
        });

        try {
            System.out.println(f.isDone());
            System.out.println(f.get(2000,TimeUnit.MILLISECONDS));
            System.out.println(f.isDone());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
