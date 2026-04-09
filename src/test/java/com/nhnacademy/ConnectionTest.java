package com.nhnacademy;


import core.Connection;
import core.InputPort;
import lombok.val;
import message.Message;
import node.PrintNode;

public class ConnectionTest {
    public static void main(String[] args){

        //deliver 후 target 수신
        Connection connection = new Connection();
        PrintNode printNode = new PrintNode("printer");

        Message message = new Message("msg");
        connection.deliver(message);

        //target 미설정시동작
        Connection connection1 = new Connection();
        Message message1 = new Message("msg1");
        try{
            connection1.deliver(message1);
            System.out.println("예외없음");
        } catch (Exception e){
            System.out.println("예외발생");
        }

        //버퍼크기확인
        Connection connection2 = new Connection();
        Message message2 = new Message("msg2");
        connection2.deliver(new Message("msg2"));

        //다수메세지 순서보장
        Connection connection3 = new Connection();
        PrintNode printNode1 = new PrintNode("printer1");


        connection3.deliver(new Message("1"));
        connection3.deliver(new Message("2"));
        connection3.deliver(new Message("3"));
    }

}
