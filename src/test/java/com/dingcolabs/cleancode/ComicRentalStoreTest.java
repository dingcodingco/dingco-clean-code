package com.dingcolabs.cleancode;

import com.dingcolabs.cleancode.s07_08_refactoring.ComicRentalStore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ComicRentalStoreTest {

    /** 파이썬 원본의 objective_result 와 100% 동일한 출력이어야 한다. */
    @Test
    void producesExactExpectedOutput() {
        String expected =
                "의정부고 주문 내역\n" +
                "진격의 거인 : 25000원 55 권 대여\n" +
                "원피스 : 160000원 40 권 대여\n" +
                "짱구는 못말려 : 30000원 20 권 대여\n" +
                "괴짜 가족 : 30000원 15 권 대여\n" +
                "총액 245000원 적립 포인트 43점\n \n" +
                "의여고 주문 내역\n" +
                "진격의 거인 : -10000원 20 권 대여\n" +
                "원피스 : 40000원 10 권 대여\n" +
                "짱구는 못말려 : 55000원 50 권 대여\n" +
                "괴짜 가족 : 60000원 60 권 대여\n" +
                "총액 145000원 적립 포인트 52점\n \n";

        ComicRentalStore store = new ComicRentalStore(ComicRentalStore.sampleCartoonInfo());
        assertEquals(expected, store.getResult(ComicRentalStore.sampleOrderHistories()));
    }
}
