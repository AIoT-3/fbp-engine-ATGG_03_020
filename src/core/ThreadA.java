package core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadA {
    private final BlockingQueue<String> buffer = new LinkedBlockingQueue<>();

    public void deliver() {
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    String msg = "메시지-" + i;
                    buffer.put(msg);
                    Thread.sleep(100);
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
            try{
                buffer.put("end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                while (true) {
                    String remove = buffer.take();
                    if("end".equals(remove)){
                        break;
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        });
        producer.start();
        consumer.start();
    }

}
