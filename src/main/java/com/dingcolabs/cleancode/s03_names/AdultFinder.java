package com.dingcolabs.cleancode.s03_names;

import java.util.ArrayList;
import java.util.List;

/**
 * 03. 의미 있는 이름 - "어른 찾기" 예제의 최종 클린 버전.
 *
 * <p>Dirty: get_them / the_list / x / p1 / 매직넘버 19
 * Clean : getAdults / people / person / age / 상수 ADULT_AGE
 */
public class AdultFinder {

    private static final int ADULT_AGE = 19;

    public record Person(String name, int age) {}

    public List<Person> getAdults(List<Person> people) {
        List<Person> adults = new ArrayList<>();
        for (Person person : people) {
            if (person.age() > ADULT_AGE) {
                adults.add(person);
            }
        }
        return adults;
    }

    public static void main(String[] args) {
        AdultFinder finder = new AdultFinder();
        List<Person> people = List.of(
                new Person("민수", 15),
                new Person("영희", 20),
                new Person("철수", 33));
        System.out.println(finder.getAdults(people)); // [Person[name=영희, age=20], Person[name=철수, age=33]]
    }
}
