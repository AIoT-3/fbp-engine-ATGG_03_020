package com.nhnacademy;

import node.TransformNode;

public class TransformNode_f2c{
    public static void main(String[] args) {
        TransformNode node = new TransformNode("f2c", msg -> {
            double fahrenheit = msg.get("temperature");
            double celsius = (fahrenheit - 32) * 5 / 9;
            return msg.set("temperature", celsius);
        });
    }
}
