package xyz.cedarjo.algorithms.sort;

import xyz.cedarjo.datastructure.heap.Heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * 堆排序
 */
public class HeapSort {

    public <E> void sort(E[] array, Comparator<E> comparator) {
        Heap<E> heap = new Heap<>(array, comparator);
        for (int i = 0; i < array.length; i++) {
            array[i] = heap.removeTop();
        }
    }

    public static void main(String[] args) {
        class Person {
            int age;
            String name;

            public int getAge() {
                return age;
            }

            public Person(int age, String name) {
                this.age = age;
                this.name = name;
            }

            @Override
            public String toString() {
                return "[name: " + name + ", age: " + age + "]";
            }
        }
        Person[] array = {new Person(4, "jim"),
                new Person(4, "kater"),
                new Person(2, "tom"),
                new Person(4, "lucy"),
                new Person(1, "lily"),
                new Person(4, "white")};
        new HeapSort().sort(array, Collections.reverseOrder(Comparator.comparing(Person::getAge)));
        System.out.println(Arrays.toString(array));

        // [[name: lily, age: 1], [name: tom, age: 2], [name: kater, age: 4], [name: lucy, age: 4], [name: white, age: 4], [name: jim, age: 4]]
        // 对比结果可知，排序后age=4的四人顺序改变，所以堆排序不是稳定排序算法
    }

}
