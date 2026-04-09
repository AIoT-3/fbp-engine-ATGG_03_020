package com.nhnacademy;

import core.Connection;
import message.Message;
import node.GeneratorNode;
import node.PrintNode;

public class ThreadTest {
    public static void main(String[] args) {
        // Generator -> Connection -> PrintNode 구조
        GeneratorNode generatorNode = new GeneratorNode("gen");
        Connection connection = new Connection();
        generatorNode.getOutputPort().connect(connection);
        PrintNode printNode = new PrintNode("print");

// 생산자 스레드 (1초 간격 5개)
        Thread genThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                generatorNode.generate("num", i);
                System.out.println("[Gen] 메시지 생성: " + i);
                try { Thread.sleep(1000); } catch (InterruptedException e) { break; }
            }
        });

// 소비자 스레드 (지속적으로 poll)
        Thread printThread = new Thread(() -> {
            while (printNode.isRunning()) {
                Message msg = connection.poll();
                if (msg != null) {
                    printNode.process(msg);
                }
                try { Thread.sleep(10); } catch (InterruptedException e) { break; }
            }
        });

        genThread.start();
        printThread.start();
    }
}