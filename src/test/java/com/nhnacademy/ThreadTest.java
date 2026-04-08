package com.nhnacademy;

import core.ThreadA;

public class ThreadTest {
    public static void main(String[] args) {
        ThreadA connection = new ThreadA();

        System.out.println("--- [멀티스레드 테스트 시작] ---");

        // 1. 별도의 스레드들이 시작됩니다.
        connection.deliver();

        // 2. 메인 스레드는 작업이 완료될 때까지 충분히 대기합니다.
        // 생산자가 100ms 간격으로 100개를 보내므로 최소 10초 이상 필요합니다.
        try {
            System.out.println("시스템: 생산자와 소비자가 작동 중입니다. 잠시만 기다려 주세요...");
            java.lang.Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("테스트 중단: " + e.getMessage());
            java.lang.Thread.currentThread().interrupt();
        }

        System.out.println("--- [테스트 종료] ---");
    }
}