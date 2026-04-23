package com.nhnacademy;

import core.Connection;
import core.FlowEngine;
import flow.Flow;
import node.AbstractNode;
import node.PrintNode;
import node.SplitNode;
import node.TimerNode;

import java.util.HashMap;


public class flowengine {
    public static void main(String[] args) throws InterruptedException {
        Flow flow = new Flow("splitFlow");
        flow.addNode(new TimerNode("timer", 0, 1000000))
                .addNode(new SplitNode("id", "tick", 3))
                .addNode(new PrintNode("정상"))
                .addNode(new PrintNode("경고"));

        flow.connect("timer", "out", "id", "in")
                .connect("id", "match", "정상", "in")
                .connect("id","mismatch","경고","in");
        FlowEngine engine = new FlowEngine(new HashMap<>());
        engine.register(flow);
        engine.startFlow("monitoring");
        Thread.sleep(5000);
        engine.shutdown();

    }

}
