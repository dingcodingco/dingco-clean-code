package week01;

import java.time.LocalTime;

// 04. 함수 - 1 : Extract Method 시연용 (출근하기). 가상 도메인이라 컴파일 대상은 아님 — 스크린샷 시연용.
public class CommuteDemo {

    void goingToWork(Person person, Bus bus) {
        if (!LocalTime.now().isBefore(LocalTime.of(7, 0))) {
            return;
        }

        person.wakeUpFromTheBed();   // 침대에서 일어난다 -> 일어나서 씻는다.
        person.goToBathroom();       // 화장실에 간다 -> 일어나서 씻는다.
        person.turnOnTheWater();     // 물을 켠다 -> 일어나서 씻는다.
        person.washFace();           // 얼굴을 씻는다 -> 일어나서 씻는다.
        if (!person.alreadyShowerYesterday()) {
            person.takeAShower();    // 샤워를 한다 -> 일어나서 씻는다.
        }

        if (person.gender().equals("woman")) {
            person.pullOn("클로니더블자켓");
            if (person.like("화장")) {
                person.makeUp();
            }
        } else if (person.gender().equals("man")) {
            person.pullOn("맨투맨");
            person.shave();
        }

        person.walkTo(person.nearBusStop());
        while (bus.location() == person.nearBusStop()) {
            person.wait_();
        }
        person.ride(bus);
        person.walkTo(person.company());
    }

    // 시연용 stub 타입
    interface Bus { Object location(); }
    interface Person {
        void wakeUpFromTheBed(); void goToBathroom(); void turnOnTheWater(); void washFace();
        boolean alreadyShowerYesterday(); void takeAShower();
        String gender(); void pullOn(String s); boolean like(String s); void makeUp(); void shave();
        void walkTo(Object o); Object nearBusStop(); void wait_(); void ride(Bus b); Object company();
    }
}
