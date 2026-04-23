package com.nhnacademy;

import core.Connection;
import flow.Flow;
import node.*;

public class Pipeline_flow {
    public static void main(String[] args) throws InterruptedException {

        TimerNode timerNode = new TimerNode("timer", 0, 1000000);
        LogNode logNode = new LogNode("logger");
        FilterNode filterNode = new FilterNode("filter", "tick", 3);
        PrintNode printNode = new PrintNode("출력");

        connect(timerNode, "out", logNode, "in");
        connect(logNode, "out", filterNode, "in");
        connect(filterNode, "out", printNode, "in");

        logNode.initialize();
        filterNode.initialize();
        printNode.initialize();
        timerNode.initialize();

        Thread.sleep(7000);

        timerNode.shutdown();
        logNode.shutdown();
        filterNode.shutdown();
        printNode.shutdown();

        System.out.println("=== shutdown 완료 ===");
    }

    public static void connect(AbstractNode from, String outPort, AbstractNode to, String inPort) {
        Connection connection = new Connection();
        from.getOutputPort(outPort).connect(connection);
        connection.setTarget(to.getInputPort(inPort));
        connection.start();
    }
}