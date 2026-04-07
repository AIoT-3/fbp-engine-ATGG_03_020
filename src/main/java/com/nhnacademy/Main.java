package com.nhnacademy;

import core.Connection;


import node.FilterNode;
import node.GeneratorNode;
import node.PrintNode;

public class Main {
    public static void main(String[] args) {

        GeneratorNode generatorNode = new GeneratorNode("generator");
        FilterNode filterNode = new FilterNode("filter","temperature",30);
        PrintNode printNode = new PrintNode("printer");

        Connection connection  = new Connection();
        generatorNode.getOutputPort().connect(connection);
        connection.setTarget(filterNode.getinputPort());

        Connection connection1 = new Connection();
        filterNode.getoutputPort().connect(connection1);
        connection1.setTarget(printNode.getInputPort());


        generatorNode.generate("temperature", 25.5);
        generatorNode.generate("temperature", 31);

    }

}