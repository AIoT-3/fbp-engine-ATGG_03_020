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

        Connection connection = new Connection();
        generatorNode.getOutputPort().connect(connection);
        connection.setTarget(filterNode.getinputPort());

        generatorNode.generate("num",5);
        generatorNode.generate("num",10);
        generatorNode.generate("num",15);
    }
}
