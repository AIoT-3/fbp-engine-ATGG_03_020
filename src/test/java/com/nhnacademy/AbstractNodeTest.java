package com.nhnacademy;

import core.Connection;
import core.InputPort;
import core.OutputPort;
import message.Message;
import node.AbstractNode;
import node.PrintNode;


public class AbstractNodeTest  {

    public static void main(String[] args){
        AbstractNode abstractNode = new AbstractNode("test_id") {

            @Override
            protected void onProcess(Message message) {}
        };
        if(abstractNode.getId().equals("test_id")){
            System.out.println(abstractNode.getId());
        } else{
            System.out.println("fail");
        }

        PrintNode printNode = new PrintNode("print");
        Message message = new Message("msg");
        InputPort inputPort = printNode.getInputPort("in");
        if(inputPort != null){
            inputPort.receive(message);
        } else {
            System.out.println("null");
        }

        PrintNode printNode1 = new PrintNode("print");
        Message message1 = new Message("msg");
        OutputPort outputPort = printNode1.getOutputPort("out");
        if(outputPort != null){
            outputPort.send(message1);
        } else {
            System.out.println("null");
        }

        PrintNode senderNode = new PrintNode("sender");
        PrintNode receiverNode = new PrintNode("receiver");

        Connection connection = new Connection();

        senderNode.getOutputPort("out").connect(connection);
        connection.setTarget(receiverNode.getInputPort("in"));

        senderNode.getOutputPort("out").send(new Message("Success"));
    }
}
