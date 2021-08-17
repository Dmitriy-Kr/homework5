package ua.homework5;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();

        for (int i = 0; i < 20; i++) {
            list.add(i);
        }

        System.out.println("Цикл foreach: ");
        for (Integer i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println();

        System.out.println("Обратный итератор: ");
        Iterator decItr = list.decreasingIterator();
        while (decItr.hasNext()) {
            System.out.print(decItr.next() + " ");
        }
        System.out.println();
        System.out.println();

        System.out.println("Удаление элемента по индексу. Значение элемента:  " + list.remove(12));
        System.out.println(list);
        System.out.println();

        System.out.println("Изменение элемента по индексу.");
        list.set(7, 999);
        System.out.println(list);
        System.out.println();

        System.out.println("Удаление элемента по значению.");
        System.out.println(list.remove((Integer) 14));
        System.out.println(list);
        System.out.println();

        System.out.println("Добавление элемента в указанную позицию.");
        list.add(7, 67);
        System.out.println(list);

    }
}
