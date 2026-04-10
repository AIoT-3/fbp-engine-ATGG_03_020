package com.nhnacademy;

import core.Connection;
import node.GeneratorNode;
import node.PrintNode;

public class GenerateNodeTest {
    public static void main(String[] args){
        //generate 메시지 생성
        GeneratorNode generatorNode = new GeneratorNode("gen");
        PrintNode printNode = new PrintNode("print");
        Connection connection = new Connection();

        generatorNode.getOutputPort().connect(connection);
        connection.setTarget(printNode.getInputPort("in"));

        //메시지내용확인
        generatorNode.generate("key", 10);

        //output 조회
        if(generatorNode.getOutputPort() != null){
            System.out.println("성공");
        } else {
            System.out.println("실패");
        }

        //다수generate 호출
        GeneratorNode generatorNode1 = new GeneratorNode("gen1");
        GeneratorNode generatorNode2 = new GeneratorNode("gen2");
        GeneratorNode generatorNode3 = new GeneratorNode("gen3");

        generatorNode1.getOutputPort().connect(connection);
        generatorNode2.getOutputPort().connect(connection);
        generatorNode3.getOutputPort().connect(connection);

        connection.setTarget(printNode.getInputPort("in"));

        generatorNode1.generate("key", 10);
        generatorNode2.generate("key", 20);
        generatorNode3.generate("key", 30);
    }
}
