package com.dingcolabs.cleancode.hw_bowling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * HW. 1주차 숙제 - 볼링 점수 구하기의 최종 리팩토링 버전.
 *
 * <p>입력 문자열의 각 문자: 숫자=쓰러뜨린 핀, '-'=0개, 'S'=스트라이크, 'P'=스페어.
 * "점수 구하기 / 추가 점수(보너스) 구하기 / 프레임 수 구하기" 세 가지 관심사를
 * 작은 함수로 분리한 결과물이다.
 *
 * <pre>
 * get_bowling_score("9-8P72S9P-9S-P9-SS8") == 150
 * get_bowling_score("SSSSSSSSSSSS")        == 300
 * </pre>
 */
public class BowlingScore {

    private record FrameState(int frame, int stack) {}

    public static int getBowlingScore(String s) {
        int frame = 1;
        int stack = 0;
        int answer = 0;
        List<Integer> plus = new ArrayList<>(); // 보너스 점수가 곱해질 인덱스들

        for (int i = 0; i < s.length(); i++) {
            appendIndexToPlus(frame, i, plus, s);
            FrameState state = nextFrameAndStack(frame, i, s, stack);
            frame = state.frame();
            stack = state.stack();

            answer += getScore(i, s) * (Collections.frequency(plus, i) + 1);
        }
        return answer;
    }

    private static FrameState nextFrameAndStack(int frame, int i, String s, int stack) {
        if (s.charAt(i) == 'S') {
            stack = 0;
            frame += 1;
        } else {
            stack += 1;
            if (stack == 2) {
                stack = 0;
                frame += 1;
            }
        }
        return new FrameState(frame, stack);
    }

    private static void appendIndexToPlus(int frame, int i, List<Integer> plus, String s) {
        if (s.charAt(i) == 'S') {
            if (frame < 10) {
                plus.add(i + 1);
                plus.add(i + 2);
            }
        } else if (s.charAt(i) == 'P') {
            if (frame < 10) {
                plus.add(i + 1);
            }
        }
    }

    private static int getScore(int i, String s) {
        char c = s.charAt(i);
        if (c == 'S') {
            return 10;
        }
        if (c == 'P') {
            char prev = s.charAt(i - 1);
            return prev == '-' ? 10 : 10 - (prev - '0');
        }
        if (c == '-') {
            return 0;
        }
        return c - '0';
    }

    public static void main(String[] args) {
        System.out.println("answer is " + getBowlingScore("9-8P72S9P-9S-P9-SS8")); // 150
        System.out.println("answer is " + getBowlingScore("SSSSSSSSSSSS"));        // 300
    }
}
