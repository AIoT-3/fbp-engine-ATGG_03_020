package com.nhnacademy;

import core.Connection;
import message.Message;
import node.FilterNode;
import node.GeneratorNode;
import node.PrintNode;

public class Filterthread {
    public static void main(String[] args) {
        // 1. 노드 및 컴포넌트 생성
        GeneratorNode generator = new GeneratorNode("gen");
        FilterNode filterNode = new FilterNode("filter", "num", 10);
        PrintNode printer = new PrintNode("print"); // process()가 구현된 노드

        Connection connection = new Connection();
        generator.getOutputPort().connect(connection); // Generator 출력을 Connection에 연결
        connection.setTarget(filterNode.getinputPort());

        Connection connection1 = new Connection();
        filterNode.getoutputPort().connect(connection1);
        connection1.setTarget(printer.getInputPort());

        // 3. 생산자 스레드: 1초 간격으로 메시지 생성
        Thread generate = new Thread(() -> {
            generator.generate("data", 101);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new IllegalStateException();
            }

        });

        Thread filter = new Thread(() -> {
            while(printer.isRunning()) {
                Message message = connection.poll();
                if(message != null){
                    filterNode.process(message);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new IllegalStateException();
                }
            }
        });

        // 4. 소비자 스레드: Connection에서 꺼내서 전달
        Thread consumer = new Thread(() -> {
            while (printer.isRunning()) {
                Message message = connection1.poll();
                if (message != null) {
                    printer.process(message);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        generate.start();
        filter.start();
        consumer.start();
    }
}
