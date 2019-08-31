package xyz.cedarjo.algorithms.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 冒泡排序
 * 0: [0, length)从左到右两两比较，升序不变，逆序则交换，最终 length-1位置得到最大元素
 * 1: [0, length-1)从左到右两两比较，升序不变，逆序则交换，最终 length-2位置得到最大元素
 * ...
 * i: [0, length-i)从左到右两两比较，升序不变，逆序则交换，最终 length-i-1位置得到最大元素
 * 直到length-i=2，即i=length-2
 */
public class BubbleSort {

    public <E> void sort(E[] array, Comparator<E> comparator) {
        int length = array.length;
        for (int i = 0; i < length - 1; i++) {
            // [0, length-i) 从左到右两两比较，逆序则交换
            for (int j = 1; j < length - i; j++) {
                // 比较 array[j-1]和array[j]
                if (comparator.compare(array[j - 1], array[j]) > 0) {
                    swap(array, j - 1, j);
                }
            }
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
        new BubbleSort().sort(array, Comparator.comparing(Person::getAge));
        System.out.println(Arrays.toString(array));

        // [[name: lily, age: 1], [name: tom, age: 2], [name: jim, age: 4], [name: lucy, age: 4], [name: white, age: 4]]
        // 对比结果可知，排序后age=4的三人顺序未变，所以冒泡排序是稳定排序算法
    }

}
