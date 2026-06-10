package com.dingcolabs.cleancode;

import com.dingcolabs.cleancode.s03_names.AdultFinder;
import com.dingcolabs.cleancode.s03_names.BusRiding;
import com.dingcolabs.cleancode.s05_functions2.PureFunctionVsSideEffect;
import com.dingcolabs.cleancode.s05_functions2.PureFunctionVsSideEffect.Person;
import com.dingcolabs.cleancode.s06_exception.ExceptionBasics;
import com.dingcolabs.cleancode.s06_exception.NetflixVideo;
import com.dingcolabs.cleancode.s06_exception.NetflixVideo.License;
import com.dingcolabs.cleancode.s06_exception.NetflixVideo.User;
import com.dingcolabs.cleancode.s06_exception.NetflixVideo.Video;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CleanCodeExamplesTest {

    @Test
    void pureFunctionDoesNotMutate_sideEffectDoes() {
        Person p1 = new Person(19);
        assertEquals(20, PureFunctionVsSideEffect.getOnePlusAge(p1));
        assertEquals(19, p1.age, "순수 함수는 외부 상태를 바꾸지 않는다");

        Person p2 = new Person(19);
        assertEquals(20, PureFunctionVsSideEffect.getOnePlusAgeWithSideEffect(p2));
        assertEquals(20, p2.age, "부수 효과가 있는 함수는 외부 상태를 바꾼다");
    }

    @Test
    void exceptionBasics_catchesByType() {
        assertEquals("해당 인덱스가 없습니다", ExceptionBasics.catchSeparately());
    }

    @Test
    void netflix_routesByExceptionType() {
        NetflixVideo netflix = new NetflixVideo();
        Video video = new Video("기묘한 이야기");
        assertEquals("사용권 구매 페이지로 이동", netflix.displayVideo(video, new User(false, new License(0))));
        assertEquals("메인 페이지로 이동", netflix.displayVideo(video, new User(true, new License(4))));
        assertEquals("재생: 기묘한 이야기", netflix.displayVideo(video, new User(true, new License(1))));
    }

    @Test
    void adultFinder_filtersByAge() {
        AdultFinder finder = new AdultFinder();
        List<AdultFinder.Person> adults = finder.getAdults(List.of(
                new AdultFinder.Person("민수", 15),
                new AdultFinder.Person("영희", 20),
                new AdultFinder.Person("철수", 33)));
        assertEquals(2, adults.size());
        assertEquals("영희", adults.get(0).name());
    }

    @Test
    void busRiding_paysCorrectFare() {
        BusRiding busRiding = new BusRiding();

        BusRiding.User child = new BusRiding.User(10, "강남");
        busRiding.rideBus(new BusRiding.Bus(false, "강남"), child);
        assertEquals(800, child.paidFee());
        assertFalse(child.isOnBus(), "목적지에 도착하면 내린다");

        BusRiding.User adult = new BusRiding.User(30, "역삼");
        busRiding.rideBus(new BusRiding.Bus(false, "강남"), adult);
        assertEquals(1300, adult.paidFee());
        assertTrue(adult.isOnBus(), "목적지가 아니면 계속 탑승 중");

        BusRiding.User elderly = new BusRiding.User(70, "강남");
        busRiding.rideBus(new BusRiding.Bus(false, "강남"), elderly);
        assertEquals(0, elderly.paidFee(), "경로우대는 무료");
    }
}
