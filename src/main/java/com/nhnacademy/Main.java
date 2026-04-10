package com.nhnacademy;

import core.Connection;
import message.Message;
import node.*;

public class Main {
    public static void main(String[] args) {

        FilterNode filterNode = new FilterNode("filter", "num", 10);
        PrintNode printNode = new PrintNode("print");
        TimerNode timerNode = new TimerNode("time", 1, 500);

        Connection connection = new Connection();
        timerNode.getOutputPort("out").connect(connection);
        connection.setTarget(filterNode.getInputPort("in"));

        Connection connection1 = new Connection();
        filterNode.getOutputPort("out").connect(connection1);
        connection1.setTarget(printNode.getInputPort("in"));

        printNode.initialize();
        filterNode.initialize();
        timerNode.initialize();

        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        printNode.shutdown();
        filterNode.shutdown();
        timerNode.shutdown();
    }
}