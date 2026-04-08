package com.nhnacademy;

import core.Connection;
import core.DefaultOutputPort;
import core.OutputPort;
import message.Message;
import node.GeneratorNode;
import node.PrintNode;

public class DefaultOutputTest {
    public static void main(String[] args){
        //단일 Connection전달
        GeneratorNode generatorNode = new GeneratorNode("gen");
        DefaultOutputPort outputPort = new DefaultOutputPort(generatorNode);
        Connection connection = new Connection();
        PrintNode printNode = new PrintNode("print");
        connection.setTarget(printNode.getInputPort());
        outputPort.connect(connection);

        Message message = new Message("msg");
        message.withEntry("key", 10);

        outputPort.send(message);

        //다중 Connection전달
        Connection connection1 = new Connection();
        Connection connection2 = new Connection();

        PrintNode printNode1 = new PrintNode("print1");

        connection1.setTarget(printNode1.getInputPort());
        connection2.setTarget(printNode1.getInputPort());

        outputPort.connect(connection);
        Message message1 = new Message("msg1");
        message1.withEntry("key",10);

        outputPort.send(message);

        //Connection 미연결시
        GeneratorNode generatorNode2 = new GeneratorNode("gen");
        DefaultOutputPort outputPort1 = new DefaultOutputPort(generatorNode);
        PrintNode printNode2 = new PrintNode("print");
        Message message2 = new Message("msg2");
        try{
            outputPort.send(message);
            System.out.println("예외없음");
        } catch (Exception e){
            System.out.println("예외발생");
        }

    }
}
