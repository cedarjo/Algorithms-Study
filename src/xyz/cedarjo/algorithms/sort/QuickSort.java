package xyz.cedarjo.algorithms.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * 快速排序
 */
public class QuickSort {

    /**
     * 递归实现
     * [left, right] 经过分隔操作，找到了索引partition，
     * 使得[left, partition)均小于 partition，(partition, right]均大于 partition
     * 接着只要将[left, partition)和(partition, right]排序，就得到了有序的[left, right]
     * @param array
     * @param comparator
     * @param <E>
     */
    public <E> void sortRecursive(E[] array, Comparator<E> comparator) {
        new QuickSortRecursive<>(comparator).sort(array, 0, array.length - 1);
    }

    class QuickSortRecursive<E> {
        private Comparator<E> comparator;
        private Random random;

        QuickSortRecursive(Comparator<E> comparator) {
            this.comparator = comparator;
            this.random = new Random();
        }

        void sort(E[] array, int left, int right) {
            if (left >= right) {
                return;
            }
            int partition = partition(array, left, right);
            sort(array, left, partition - 1);
            sort(array, partition + 1, right);
        }

        /**
         * 区间[left, right]分为4部分
         * [left, left], (left, partition], (partition, i), [i, right]
         * left为基准
         * (left, partition]均小于等于基准
         * (parition, i)均大于基准
         * [i, right]为待处理区间
         * 如果i大于基准，i自增即可
         * 如果i小于等于基准，将i追加到(left, partition]区间（即i与partition+1交换，之后partition和i均自增）
         * 初始化状态下(left, partition]为空，(partition, i)也为空
         * 即partition=left, i=left+1
         * @param array
         * @param left
         * @param right
         * @return
         */
        private int partition(E[] array, int left, int right) {
            // 在[left, right]取一个随机索引，并和left交换
            swap(array, random.nextInt(right - left + 1) + left, left);

            // 初始化partition
            int partition = left;
            E base = array[left]; // 基准
            for (int i = left + 1; i <= right; i++) {
                if (comparator.compare(array[i], base) <= 0) {
                    // 当前元素比基准小，追加到(left, partition]
                    swap(array, partition + 1, i); // 小的放前，大的放后
                    partition++;
                } else {
                    // 无操作
                }
            }
            swap(array, left, partition);
            return partition;
        }

        private void swap(E[] array, int indexA, int indexB) {
            E temp = array[indexA];
            array[indexA] = array[indexB];
            array[indexB] = temp;
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
        new QuickSort().sortRecursive(array, Comparator.comparing(Person::getAge));
        // new QuickSort().sortNonRecursive(array, Comparator.comparing(Person::getAge));
        System.out.println(Arrays.toString(array));

        // [[name: lily, age: 1], [name: tom, age: 2], [name: lucy, age: 4], [name: jim, age: 4], [name: white, age: 4]]
        // 对比结果可知，排序后age=4的三人顺序改变，所以快速排序不是稳定排序算法
    }

}
