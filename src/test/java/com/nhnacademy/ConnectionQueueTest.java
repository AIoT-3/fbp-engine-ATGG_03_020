package com.nhnacademy;

import core.Connection;
import message.Message;
import node.PrintNode;

public class ConnectionQueueTest {
    public static void main(String[] args) {
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
            Message msg = connection1.poll();
            System.out.println(msg);

        });
        thread1.start();

        //멀티스레드 deliver-poll

        //poll 대기 동작
        //버퍼 크기 제한
        //버퍼 크기 조회
    }


}
