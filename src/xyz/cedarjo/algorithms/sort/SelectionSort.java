package xyz.cedarjo.algorithms.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 选择排序
 * 从 [0, length)取最小值，与0交换
 * 从 [1, length)取最小值，与1交换
 * ...
 * 从 [i, length)取最小值，与i交换
 * 直到i=length-2
 */
public class SelectionSort {

    public <E> void sort(E[] array, Comparator<E> comparator) {
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            // 取[i, length)的最小值，与i交换
            E min = array[i];
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (comparator.compare(array[j], min) < 0) {
                    min = array[j];
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
    }

    private <E> void swap(E[] array, int indexA, int indexB) {
        E temp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = temp;
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
                new Person(2, "tom"),
                new Person(4, "lucy"),
                new Person(1, "lily"),
                new Person(4, "white")};
        new SelectionSort().sort(array, Comparator.comparing(Person::getAge));
        System.out.println(Arrays.toString(array));

        // [[name: lily, age: 1], [name: tom, age: 2], [name: lucy, age: 4], [name: jim, age: 4], [name: white, age: 4]]
        // 对比结果可知，排序后age=4的三人顺序被打乱，所以选择排序不是稳定排序
    }

}
