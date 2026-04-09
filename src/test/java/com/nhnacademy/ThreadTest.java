package com.nhnacademy;

import core.Connection;
import message.Message;
import node.GeneratorNode;

public class ThreadTest {
    public static void main(String[] args) {
        GeneratorNode generator = new GeneratorNode("gen-1"); // 사용자님 코드 활용
        Connection connection = new Connection();


        generator.getOutputPort().connect(connection);

        connection.consumer(); // 소비자 스레드 시작 (while 루프 대기)
        generator.producer();          // 생산자 스레드 시작 (1초 간격 메시지 생성)

        try { Thread.sleep(6000); } catch (InterruptedException e) {}
    }
}