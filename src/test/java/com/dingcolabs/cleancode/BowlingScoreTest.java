package com.dingcolabs.cleancode;

import com.dingcolabs.cleancode.hw_bowling.BowlingScore;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BowlingScoreTest {

    /** 파이썬 원본의 assert 와 동일한 케이스. */
    @Test
    void matchesOriginalAsserts() {
        assertEquals(150, BowlingScore.getBowlingScore("9-8P72S9P-9S-P9-SS8"));
        assertEquals(300, BowlingScore.getBowlingScore("SSSSSSSSSSSS"));
    }

    @Test
    void allGutters() {
        assertEquals(0, BowlingScore.getBowlingScore("--------------------"));
    }
}
