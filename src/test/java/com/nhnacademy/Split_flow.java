package com.nhnacademy;

import core.Connection;
import node.AbstractNode;
import node.PrintNode;
import node.SplitNode;
import node.TimerNode;


public class Split_flow {
    public static void main(String[] args) {
        TimerNode timerNode = new TimerNode("time", 0, 1000000);
        SplitNode splitNode = new SplitNode("id", "tick", 3);
        PrintNode match = new PrintNode("경고");
        PrintNode mismatch = new PrintNode("정상");
        connect(timerNode,"out",splitNode,"in");
        connect(splitNode, "match", match, "in");
        connect(splitNode, "mismatch", mismatch, "in");
        timerNode.initialize();
    }

    public static void connect(AbstractNode from, String outPort, AbstractNode to, String inPort) {
        Connection connection = new Connection();
        from.getOutputPort(outPort).connect(connection);
        connection.setTarget(to.getInputPort(inPort));
    }

}
