package com.nhnacademy;

import core.Connection;
import core.DefaultInputPort;
import message.Message;
import node.GeneratorNode;
import node.PrintNode;

public class PrintNodeTest {
    public static void main(String[] args){
        //input 조회
        GeneratorNode generatorNode = new GeneratorNode("gen");
        PrintNode printNode = new PrintNode("print");
        Connection connection = new Connection();

        generatorNode.getOutputPort().connect(connection);
        connection.setTarget(printNode.getInputPort());

        if(printNode.getInputPort() != null){
            System.out.println("성공");
        } else{
            System.out.println("실패");
        }

        //inputPort를 통한 수신
        DefaultInputPort defaultInputPort = new DefaultInputPort("in",printNode);
        Message message = new Message("msg");
        defaultInputPort.receive(message);
    }
}
