package xyz.cedarjo.algorithms.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 插入排序
 * 1: [0, 1]取出1，向前比较，如果逆序，前边元素后移一位，直到升序或前边无元素，将1落地，由此保证升序
 * 2: [0, 2]取出2，向前比较，如果逆序，前边元素后移一位，直到升序或前边无元素，将2落地，由此保证升序
 * ...
 * i: [0, i]取出i，向前比较，如果逆序，前边元素后移一位，直到升序或前边无元素，将i落地，由此保证升序
 * 直到i=length-1
 */
public class InsertionSort {

    public <E> void sort(E[] array, Comparator<E> comparator) {
        int length = array.length;
        for (int i = 1; i < length; i++) {
            E temp = array[i];
            int pos = i - 1; // 待比较元素索引
            while (pos >= 0 && comparator.compare(array[pos], temp) > 0) {
                array[pos + 1] = array[pos];
                pos--;
            }
            array[pos + 1] = temp;
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
                new Person(2, "tom"),
                new Person(4, "lucy"),
                new Person(1, "lily"),
                new Person(4, "white")};
        new InsertionSort().sort(array, Comparator.comparing(Person::getAge));
        System.out.println(Arrays.toString(array));

        // [[name: lily, age: 1], [name: tom, age: 2], [name: jim, age: 4], [name: lucy, age: 4], [name: white, age: 4]]
        // 对比结果可知，排序后age=4的三人顺序未变，所以插入排序是稳定排序算法
        // 插入排序降低了交换了次数，提升了性能
    }

}
