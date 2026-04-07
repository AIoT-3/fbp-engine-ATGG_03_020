package com.nhnacademy;

import message.Message;

public class MessageTest {
    public static void main(String[] args) {
        Message msg = new Message("MSG-001");
        System.out.println("생성 직후: " + msg);

        msg.withEntry("sensor_type", "Temperature");
        msg.withEntry("value", 25.5);
        msg.withEntry("is_active", true);
        System.out.println("데이터 추가 후: " + msg);

        String type = msg.get("sensor_type");
        Double val = msg.get("value");
        boolean active = msg.get("is_active");

        System.out.println("타입: " + type);
        System.out.println("값: " + val);
        System.out.println("활성화 여부: " + active);

        System.out.println("sensor_type 존재함? " + msg.hasKey("sensor_type"));
        System.out.println("unknown_key 존재함? " + msg.hasKey("unknown_key"));
    }
}