package com.nhnacademy;

import message.Message;
import node.AbstractNode;

public class AbstractNodeTest  {
    public static void main(String[] args){
        AbstractNode abstractNode = new AbstractNode("test") {

            @Override
            protected void onProcess(Message message) {

            }
        };
    }
}
