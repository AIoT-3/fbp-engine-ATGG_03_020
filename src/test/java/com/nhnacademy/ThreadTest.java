package com.nhnacademy;

import core.Connection;
import message.Message;
import node.GeneratorNode;
import node.PrintNode;

public class ThreadTest {
    public static void main(String[] args) {
        // 1. 노드 및 컴포넌트 생성
        GeneratorNode generator = new GeneratorNode("Gen-1");
        PrintNode printer = new PrintNode("Print-1"); // process()가 구현된 노드
        Connection connection = new Connection();

        // 2. 조립 (Wiring)
        generator.getOutputPort().connect(connection); // Generator 출력을 Connection에 연결
        // Connection의 target을 PrintNode의 InputPort로 설정하는 과정이 필요할 수 있음

        // 3. 생산자 스레드: 1초 간격으로 메시지 생성
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                generator.generate("data", "Msg-" + i);
                try { Thread.sleep(1000); } catch (InterruptedException e) { break; }
            }
        });

        // 4. 소비자 스레드: Connection에서 꺼내서 전달
        Thread consumer = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                Message msg = connection.poll();
                if (msg != null) {
                    // Connection이 가진 target(InputPort)에게 메시지 전달
                    // 혹은 직접 printer.process(msg) 호출

                    printer.process(msg);

                }
                try { Thread.sleep(10); } catch (InterruptedException e) { break; }
            }
        });

        producer.start();
        consumer.start();
    }
}