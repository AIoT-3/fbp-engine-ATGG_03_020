package com.nhnacademy;

import core.Connection;
import message.Message;
import node.PrintNode;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ConnectionQueueTest {
    public static void main(String[] args) throws InterruptedException {
        Connection connection = new Connection();
        PrintNode printNode = new PrintNode("printer");
        Message message = new Message("msg");
        connection.deliver(message);

        //deliver-poll 기본 동작
        Thread thread = new Thread(()->{
            Message msg = connection.poll();
            System.out.println(msg);
        });
        thread.start();

        //메시지 순서 보장
        Connection connection1 = new Connection();
        Message message1 = new Message("msg1");
        Message message2 = new Message("msg2");
        connection1.deliver(message1);
        connection1.deliver(message2);

        Thread thread1 = new Thread(()->{
            Message msg1 = connection1.poll();
            Message msg2 = connection1.poll();
            System.out.println(msg1);
            System.out.println(msg2);

        });
        thread1.start();

        //멀티스레드 deliver-poll
        Connection connection2 = new Connection();
        Message message3 = new Message("msg3");
        CountDownLatch latch = new CountDownLatch(1);
        Thread poll = new Thread(()->{
            try{
                Message msg3 = connection2.poll();
                System.out.println(msg3);
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }

        });

        Thread deliver = new Thread(()->{
            try{
                Thread.sleep(300);
                connection2.deliver(message3);
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        poll.start();
        deliver.start();

        //poll 대기 동작
        Connection connection3 = new Connection();
        Message message4 = new Message("msg4");
        CountDownLatch latch1 = new CountDownLatch(1);

        Thread poll1 = new Thread(()->{
            Message message5 = null;
            while(message5 ==null) {
                message5 = connection3.poll();
            }
            System.out.println(message5);
            latch1.countDown();
        });
        poll1.start();

        Thread.sleep(1000);
        connection3.deliver(message4);

        //버퍼 크기 제한,버퍼크기조회
        Connection connection4 = new Connection(2);

        Message message5 = new Message("msg5");
        Message message6 = new Message("msg6");
        Message message7 = new Message("msg7");

        Thread poll2 = new Thread(()->{
            connection4.deliver(message5);
            System.out.println("1번");
            System.out.println(connection4.getBufferSize());
            connection4.deliver(message6);
            System.out.println("2번");
            System.out.println(connection4.getBufferSize());
            connection4.deliver(message7);
            System.out.println("3번");
            System.out.println(connection4.getBufferSize());
        });
        poll2.start();

        Thread.sleep(1000);
        Message msg = connection4.poll();
        System.out.println(connection4.getBufferSize());

    }

}
