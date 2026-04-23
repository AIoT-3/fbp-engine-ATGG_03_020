package com.nhnacademy;

import flow.Flow;
import node.*;

public class pipelineflow {
    public static void main(String[] args) throws InterruptedException {
        Flow flow = new Flow("pipeline_flow");
        flow.addNode(new TimerNode("timer", 0, 1000000))
                .addNode(new FilterNode("filter", "tick", 3))
                .addNode(new PrintNode("printer"));

        flow.connect("timer", "out", "filter", "in")
                .connect("filter", "out", "printer", "in");

        flow.initialize();
        Thread.sleep(7000);
        flow.shutdown();
        System.out.println("=== shutdown 완료 ===");

    }

}