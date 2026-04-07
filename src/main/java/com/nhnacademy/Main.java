package com.nhnacademy;

import core.Node;
import message.Message;
import node.PrintNode;

public class Main {
    public static void main(String[] args) {

        Node node = new PrintNode("node 1");//[ID - node 1]

        Message message = new Message("node 1");//id=node 1
        message.withEntry("sensor ", " temperature");//senseor=temperature
        message.withEntry("value", " "+22);//value=22

        String result = node.process(message);
        System.out.println(result);

        System.out.println("Temperature: " + message.get("value"));}//제네릭사용
}