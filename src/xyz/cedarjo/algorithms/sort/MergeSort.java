package xyz.cedarjo.algorithms.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 归并排序
 */
public class MergeSort {

    /**
     * 递归实现
     * [left, right] 假如有中间索引 mid，
     * 只要[left, mid] 和　(mid, right]均有序，
     * 将两个有序子数组合并就得到了有序的[left, right]
     * @param array
     * @param comparator
     * @param <E>
     */
    public <E> void sortRecursive(E[] array, Comparator<E> comparator) {
        new MergeSortRecursive<>(comparator).sort(array, 0, array.length - 1);
    }

    class MergeSortRecursive<E> {

        private Comparator<E> comparator;

        MergeSortRecursive(Comparator<E> comparator) {
            this.comparator = comparator;
        }

        void sort(E[] array, int left, int right) {
            if (left >= right) {
                return;
            }
            int mid = (right - left) / 2 + left;
            sort(array, left, mid);
            sort(array, mid + 1, right);
            merge(array, left, mid, right);
        }

        /**
         * 合并子数组 [left, mid] 和 (mid, right]
         * @param array
         * @param left
         * @param mid
         * @param right
         */
        void merge(E[] array, int left, int mid, int right) {
            // 创建一个临时数组，暂存合并后的结果
            E[] buf = (E[]) new Object[right - left + 1];
            int posA = left;
            int posB = mid + 1;
            int posBuf = 0;
            while (posA <= mid && posB <= right) {
                // 为保证排序的稳定性，针对等于的情况，取前者
                if (comparator.compare(array[posA], array[posB]) <= 0) {
                    buf[posBuf++] = array[posA++];
                } else {
                    buf[posBuf++] = array[posB++];
                }
            }
            // 剩余元素
            while (posA <= mid) {
                buf[posBuf++] = array[posA++];
            }
            while (posB <= right) {
                buf[posBuf++] = array[posB++];
            }
            // 将buf赋值到[left, right]中
            for (int i = 0; i < buf.length; i++) {
                array[left + i] = buf[i];
            }
        }
    }

    /**
     * 非递归实现
     * 合并前元素个数   1: 合并[0, 0]和[1, 1]实现[0, 1]排序，合并[2, 2]和[3, 3]实现[2, 3]排序...
     * 合并前元素个数   2: 合并[0, 1]和[2, 3]实现[0, 3]排序，合并[4, 5]和[6, 7]实现[4, 7]排序...
     * 合并前元素个数   4: 合并[0, 3]和[4, 7]实现[0, 7]排序...
     * 合并前元素个数size: 合并[0, size-1]和[size, 2*size-1]实现[0, 2*size-1]排序，合并[2*size, ...
     * 最终实现[0, length)排序
     * @param array
     * @param comparator
     * @param <E>
     */
    public <E> void sortNonRecursive(E[] array, Comparator<E> comparator) {
        new MergeSortNonRecursive<>(comparator).sort(array);
    }

    class MergeSortNonRecursive<E> {

        private Comparator<E> comparator;

        MergeSortNonRecursive(Comparator<E> comparator) {
            this.comparator = comparator;
        }

        void sort(E[] array) {
            int length = array.length;
            for (int size = 1; size < length; size += size) {
                // [i, i+size-1]与[i+size, i+size+size-1]合并，需要保证i+size不越界
                for (int i = 0; i + size < length; i += size * 2) {
                    // i+2*size-1不越界
                    merge(array, i, i + size - 1, Math.min(i + 2 * size - 1, length - 1));
                }
            }
        }

        /**
         * 合并子数组 [left, mid] 和 (mid, right]
         * @param array
         * @param left
         * @param mid
         * @param right
         */
        void merge(E[] array, int left, int mid, int right) {
            // 创建一个临时数组，暂存合并后的结果
            E[] buf = (E[]) new Object[right - left + 1];
            int posA = left;
            int posB = mid + 1;
            int posBuf = 0;
            while (posA <= mid && posB <= right) {
                // 为保证排序的稳定性，针对等于的情况，取前者
                if (comparator.compare(array[posA], array[posB]) <= 0) {
                    buf[posBuf++] = array[posA++];
                } else {
                    buf[posBuf++] = array[posB++];
                }
            }
            // 剩余元素
            while (posA <= mid) {
                buf[posBuf++] = array[posA++];
            }
            while (posB <= right) {
                buf[posBuf++] = array[posB++];
            }
            // 将buf赋值到[left, right]中
            for (int i = 0; i < buf.length; i++) {
                array[left + i] = buf[i];
            }
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
        // new MergeSort().sortRecursive(array, Comparator.comparing(Person::getAge));
        new MergeSort().sortNonRecursive(array, Comparator.comparing(Person::getAge));
        System.out.println(Arrays.toString(array));

        // [[name: lily, age: 1], [name: tom, age: 2], [name: jim, age: 4], [name: lucy, age: 4], [name: white, age: 4]]
        // 对比结果可知，排序后age=4的三人顺序未变，所以归并排序是稳定排序算法
    }

}
