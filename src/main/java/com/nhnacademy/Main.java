package com.nhnacademy;

import core.Connection;
import core.Node;
import message.Message;

import node.GeneratorNode;
import node.PrintNode;

public class Main {
    public static void main(String[] args) {

        Node node = new PrintNode("in");
        GeneratorNode generatorNode = new GeneratorNode("generator");
        PrintNode printNode = new PrintNode("printer");
        Connection connection  = new Connection();
        generatorNode.getOutputPort().connect(connection);
        connection.setTarget(printNode.getInputPort());

        generatorNode.generate("temperature", 25.5);


//        Message message = new Message("node 1");
//        message.withEntry("sensor ", " temperature");
//        message.withEntry("value", " "+22);
//
//        String result = node.process(message);
//        System.out.println(result);
//
//        System.out.println("Temperature: " + message.get("value"));//제네릭사용



    }

}