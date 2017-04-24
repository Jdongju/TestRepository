package ch18.exam26;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecuteExample {

    public static void main(String[] args) throws InterruptedException, IOException {

        //스레드풀 생성
        ExecutorService executorServiece = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 3000; i++) {
            //작업생성 코드
            Runnable task = () -> {
                System.out.println(Thread.currentThread().getName() + "작업처리");

            };

            //작업처리 지시(작업큐에 넣기)
            executorServiece.submit(task);
        }

        //스레드풀 종료
//        Thread.sleep(1000);
        System.in.read();
        executorServiece.shutdown();

    }
}
