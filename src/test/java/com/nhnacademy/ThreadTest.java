package com.nhnacademy;

import core.Connection;
import message.Message;
import node.GeneratorNode;

public class ThreadTest {
    public static void main(String[] args) {
        // 1. 부품 준비
        GeneratorNode generator = new GeneratorNode("Gen-1");
        Connection connection = new Connection();

        // 주의: GeneratorNode의 OutputPort가 이 connection을 사용하도록
        // 연결하는 작업이 내부적으로 되어 있어야 합니다. (예: DefaultOutputPort 내부 로직)

        // 2. 생산자 스레드 (GeneratorNode 실행)
        Thread producerThread = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                // 1초 간격으로 메시지 생성
                generator.generate("count", i);
                System.out.println("[생산자] 메시지 발송 완료: ID = " + i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        // 3. 소비자 스레드 (Connection에서 poll)
        Thread consumerThread = new Thread(() -> {
            while (true) {
                // Connection에서 메시지 꺼내기
                Message msg = connection.poll();

                if (msg != null) {
                    // Message.java의 get() 메서드를 활용하여 데이터 추출
                    Integer value = msg.get("count");
                    System.out.println("[소비자] 메시지 수신: ID=" + msg.getId() + ", 값=" + value);
                }

                // CPU 과부하 방지를 위한 아주 짧은 휴식
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {}
            }
        });

        // 4. 엔진 가동
        producerThread.start();
        consumerThread.start();
    }
}