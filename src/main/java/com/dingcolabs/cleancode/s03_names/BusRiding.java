package com.dingcolabs.cleancode.s03_names;

/**
 * 03. 의미 있는 이름 - "버스 타기" 예제의 최종 클린 버전.
 *
 * <p>Dirty: func / obj / var / prop / fun / fun2 / fun3 / 매직넘버(800, 1300, 19, 65)
 * Clean : rideBus / bus / user / isFull / getOn / pay / getOff / 의미 있는 상수
 */
public class BusRiding {

    private static final int ADULT_AGE = 19;
    private static final int CHILD_FEE = 800;
    private static final int ADULT_FEE = 1300;
    private static final int THE_ELDERLY = 65;

    public static class Bus {
        private final boolean full;
        private final String location;

        public Bus(boolean full, String location) {
            this.full = full;
            this.location = location;
        }

        public boolean isFull() {
            return full;
        }

        public String location() {
            return location;
        }
    }

    public static class User {
        private final int age;
        private final String destination;
        private boolean onBus;
        private int paidFee;

        public User(int age, String destination) {
            this.age = age;
            this.destination = destination;
        }

        public int age() {
            return age;
        }

        public String destination() {
            return destination;
        }

        public void getOn(Bus bus) {
            this.onBus = true;
        }

        public void pay(int fee) {
            this.paidFee = fee;
        }

        public void getOff(Bus bus) {
            this.onBus = false;
        }

        public boolean isOnBus() {
            return onBus;
        }

        public int paidFee() {
            return paidFee;
        }
    }

    public void rideBus(Bus bus, User user) {
        if (bus.isFull()) {
            return;
        }
        user.getOn(bus);

        if (user.age() < ADULT_AGE) {
            user.pay(CHILD_FEE);
        } else if (user.age() > THE_ELDERLY) {
            // 경로우대: 무료
        } else {
            user.pay(ADULT_FEE);
        }

        if (bus.location().equals(user.destination())) {
            user.getOff(bus);
        }
    }
}
