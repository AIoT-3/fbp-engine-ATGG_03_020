package com.nhnacademy;

import core.Connection;
import flow.Flow;
import node.AbstractNode;
import node.PrintNode;
import node.SplitNode;
import node.TimerNode;


public class Split_flow2 {
    public static void main(String[] args) throws InterruptedException {
        Flow flow = new Flow("splitFlow");
        flow.addNode(new TimerNode("timer", 0, 1000000))
                .addNode(new SplitNode("id", "tick", 3))
                .addNode(new PrintNode("정상"))
                .addNode(new PrintNode("경고"));

        flow.connect("timer", "out", "id", "in")
                .connect("id", "match", "정상", "in")
                .connect("id","mismatch","경고","in");

        flow.initialize();
        Thread.sleep(7000);
        flow.shutdown();
        System.out.println("=== shutdown 완료 ===");

    }

}
