package com.dingcolabs.cleancode.s06_exception;

/**
 * 06. 예외 / 20) 예외 처리 기법.
 *
 * <p>파이썬의 try/except 에 대응하는 Java 의 try/catch 예제.
 * 여러 예외를 각각 잡거나, 하나로 묶어서 잡을 수 있다.
 */
public class ExceptionBasics {

    /** 예외별로 따로 잡기 (파이썬: except ZeroDivisionError / except IndexError) */
    public static String catchSeparately() {
        try {
            int[] arr = {1, 2};
            System.out.println(arr[3]); // IndexOutOfBoundsException
            int x = 4 / 0;              // ArithmeticException
            return String.valueOf(x);
        } catch (ArithmeticException e) {
            return "0으로 나눌 수 없습니다.";
        } catch (IndexOutOfBoundsException e) {
            return "해당 인덱스가 없습니다";
        }
    }

    /** 여러 예외를 하나로 묶어서 잡기 (파이썬: except (ZeroDivisionError, IndexError) as e) */
    public static String catchTogether() {
        try {
            int[] arr = {1, 2};
            System.out.println(arr[3]);
            int x = 4 / 0;
            return String.valueOf(x);
        } catch (ArithmeticException | IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        System.out.println(catchSeparately()); // 해당 인덱스가 없습니다
        System.out.println(catchTogether());
    }
}
