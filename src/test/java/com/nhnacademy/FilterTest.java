package com.nhnacademy;

import core.Connection;
import message.Message;
import node.FilterNode;
import node.GeneratorNode;
import node.PrintNode;

public class FilterTest {
    public static void main(String[] args) {
        GeneratorNode generatorNode = new GeneratorNode("gen");
        FilterNode filterNode = new FilterNode("filter", "num", 10);
        PrintNode printNode = new PrintNode("print");

        // 1. 생성 -> 필터 연결
        Connection connection = new Connection();
        generatorNode.getOutputPort().connect(connection);
        connection.setTarget(filterNode.getinputPort());

        // 2. 필터 -> 출력 연결
        Connection connection1 = new Connection();
        filterNode.getoutputPort().connect(connection1);
        connection1.setTarget(printNode.getInputPort());

        // Thread-1: Generator
        Thread t1 = new Thread(() -> {
            while (printNode.isRunning()) {
                generatorNode.generate("num", (int)(Math.random() * 20)); // 무작위 숫자 생성
                try { Thread.sleep(500); } catch (InterruptedException e) { break; }
                System.out.print(connection.getBufferSize());
            }
        });

// Thread-2: Filter (중간 가공)
        Thread t2 = new Thread(() -> {
            while (printNode.isRunning()) {
                Message msg = connection.poll();
                if (msg != null) {
                    filterNode.process(msg); // FilterNode 내부에서 조건 통과 시 conn2로 전송함
                }
                try { Thread.sleep(10); } catch (InterruptedException e) { break; }
            }
        });

// Thread-3: Print (최종 소비)
        Thread t3 = new Thread(() -> {
            while (printNode.isRunning()) {
                Message msg = connection1.poll();
                if (msg != null) {
                    printNode.process(msg);
                }
                try { Thread.sleep(10); } catch (InterruptedException e) { break; }
            }
        });

        t1.start();
        t2.start();
        t3.start();

    }
}