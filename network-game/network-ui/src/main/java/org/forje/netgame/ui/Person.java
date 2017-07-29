package org.forje.netgame.ui;

public class Person {
    String name;

    public Person(String personName) {
        name = personName;
    }

    public String greet(String yourName) {
        return String.format("Hi %s, my name is %s", name, yourName);
    }


    public static void main(String[] args) {
        final Person person = new Person("Dr Livingston");
        final String greet = person.greet("Mr Peabody");
        System.out.println(greet);
    }

}