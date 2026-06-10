package com.dingcolabs.cleancode.s05_functions2;

/**
 * 05. 함수 - 2 / 14) 부수 효과를 일으키지 마라!
 *
 * <p>순수 함수는 외부 상태(person)를 바꾸지 않는다.
 * 부수 효과가 있는 함수는 인자로 받은 객체의 상태를 바꿔버린다.
 */
public class PureFunctionVsSideEffect {

    /** 파이썬 dict {'age': ...} 를 대신하는 가변 객체 */
    public static class Person {
        public int age;

        public Person(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "{age=" + age + "}";
        }
    }

    /** 순수 함수: person 을 변경하지 않는다. */
    public static int getOnePlusAge(Person person) {
        return person.age + 1;
    }

    /** 부수 효과: 외부 상태(person.age)를 변경한다. */
    public static int getOnePlusAgeWithSideEffect(Person person) {
        person.age += 1;
        return person.age;
    }

    public static void main(String[] args) {
        Person person1 = new Person(19);
        System.out.println(getOnePlusAge(person1));        // 20
        System.out.println("person1 is " + person1);       // person1 is {age=19}

        Person person2 = new Person(19);
        System.out.println(getOnePlusAgeWithSideEffect(person2)); // 20
        System.out.println("person2 is " + person2);              // person2 is {age=20}
    }
}
