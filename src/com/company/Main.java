package com.company;

public class Main {

    public static void main(String[] args) {

        MyList<Integer> myList = new MyListImpl<>();

        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(6);
        myList.add(7);



        for (Integer value : myList) {
            System.out.println(value);

        }

        myList.iterator().forEachRemaining(System.out::println);
    }
}
