package com.nhnacademy;

import core.Connection;
import node.FilterNode;
import node.GeneratorNode;
import node.PrintNode;

public class FilterNodeTest {
    public static void main(String[] args){
        GeneratorNode generatorNode = new GeneratorNode("gen");
        FilterNode filterNode = new FilterNode("filter","num",10);
        PrintNode printNode = new PrintNode("print");

        //생성->필터
        Connection connection = new Connection();
        generatorNode.getOutputPort().connect(connection);
        connection.setTarget(filterNode.getinputPort());

        //필터->출력
        Connection connection1 = new Connection();
        filterNode.getoutputPort().connect(connection1);
        connection1.setTarget(printNode.getInputPort());

        generatorNode.generate("num",5);
        generatorNode.generate("num",10);
        generatorNode.generate("num",15);
        try{
            generatorNode.generate(null,10);
            System.out.println("무시됨");
        } catch (Exception e) {
            System.out.println("예외처리");
        }
    }
}
