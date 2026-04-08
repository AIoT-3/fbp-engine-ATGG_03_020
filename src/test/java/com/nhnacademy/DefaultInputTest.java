package com.nhnacademy;

import core.Connection;
import core.DefaultInputPort;
import message.Message;
import node.PrintNode;

public class DefaultInputTest {
    public static void main(String[] args){
        //receive시 owner호출
        PrintNode printNode = new PrintNode("print");
        Message message = new Message("msg");
        DefaultInputPort defaultInputPort = new DefaultInputPort("in",printNode);

        defaultInputPort.receive(message);

        //포트이름확인
        DefaultInputPort defaultInputPort1 = new DefaultInputPort("in",printNode);
        String name = defaultInputPort1.getName();
        System.out.println(name);

    }
}
