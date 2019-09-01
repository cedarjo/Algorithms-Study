package xyz.cedarjo.algorithms.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * 快速排序
 * [left, right] 经过分隔操作，找到了索引partition，
 * 使得[left, partition)均小于 partition，(partition, right]均大于 partition
 * 接着只要将[left, partition)和(partition, right]排序，就得到了有序的[left, right]
 */
public class QuickSort {

    public <E> void sort1way(E[] array, Comparator<E> comparator) {
        new Quick1way<>(comparator).sort(array, 0, array.length - 1);
    }

    /**
     * 单路快排
     * @param <E>
     */
    class Quick1way<E> {
        private Comparator<E> comparator;
        private Random random;

        Quick1way(Comparator<E> comparator) {
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
         * (partition, i)均大于基准
         * [i, right]为待处理区间
         * <p>
         * 如果i大于基准，i自增即可
         * 如果i小于等于基准，将i追加到(left, partition]区间（即i与partition+1交换，之后partition和i均自增）
         * <p>
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

    public <E> void sort2way(E[] array, Comparator<E> comparator) {
        new Quick2way<>(comparator).sort(array, 0, array.length - 1);
    }

    /**
     * 双路快排
     * @param <E>
     */
    class Quick2way<E> {
        private Comparator<E> comparator;
        private Random random;

        Quick2way(Comparator<E> comparator) {
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
         * [left, left], (left, i), [i, j], (j, right]
         * left为基准
         * (left, i)均小于等于基准
         * (j, right]均大于基准
         * [i, j]为待处理区间
         * <p>
         * 如果i小于等于基准，i自增，直到i大于基准
         * 如果j大于基准，j自减，直到j小于等于基准
         * 此时i与j元素交换，并i自增ｊ自减
         * 直到i与j交叉
         * 此时partition=i-1=j
         * <p>
         * 初始化 i=left+1, j=right
         * @param array
         * @param left
         * @param right
         * @return
         */
        private int partition(E[] array, int left, int right) {
            // 在[left, right]取一个随机索引，并和left交换
            swap(array, random.nextInt(right - left + 1) + left, left);

            // 初始化 i, j
            int i = left + 1;
            int j = right;
            E base = array[left]; // 基准
            while (true) {
                while (i <= j && comparator.compare(array[i], base) <= 0) {
                    i++;
                }
                while (i <= j && comparator.compare(array[j], base) > 0) {
                    j--;
                }
                if (i > j) {
                    break;
                }
                swap(array, i, j);
                i++;
                j--;
            }

            swap(array, left, j);
            return j;
        }

        private void swap(E[] array, int indexA, int indexB) {
            E temp = array[indexA];
            array[indexA] = array[indexB];
            array[indexB] = temp;
        }

    }

    public <E> void sort3way(E[] array, Comparator<E> comparator) {
        new Quick3way<>(comparator).sort(array, 0, array.length - 1);
    }

    /**
     * 三路快排
     * 适用于存在大量重复元素
     * @param <E>
     */
    class Quick3way<E> {
        private Comparator<E> comparator;
        private Random random;

        Quick3way(Comparator<E> comparator) {
            this.comparator = comparator;
            this.random = new Random();
        }

        void sort(E[] array, int left, int right) {
            if (left >= right) {
                return;
            }
            int[] partition = partition(array, left, right);
            sort(array, left, partition[0] - 1);
            sort(array, partition[1] + 1, right);
        }

        /**
         * 区间[left, right]分为5部分
         * [left, left], (left, lt], (lt, i), [i, gt], (gt, right]
         * left为基准
         * (left, lt]均小于等于基准
         * (lt, i)均等于基准
         * (gt, right]均大于基准
         * [i, gt]为待处理区间
         * <p>
         * 当i等于基准时，i自增
         * 当i小于基准时，i与lt+1交换，i与lt均自增
         * 当i大于基准时，i与gt交换，gt自减
         * 直到i大于gt结束
         * 最后将left与lt交换，达到[left, lt)均小于基准，[lt, gt]等于基准，(gt, right]大于基准
         * 最终返回lt和gt
         * <p>
         * 初始化时lt=left, i=left+1, gt=right
         * @param array
         * @param left
         * @param right
         * @return
         */
        private int[] partition(E[] array, int left, int right) {
            // 在[left, right]取一个随机索引，并和left交换
            swap(array, random.nextInt(right - left + 1) + left, left);

            // 初始化partition
            int lt = left;
            int gt = right;
            E base = array[left]; // 基准
            for (int i = left + 1; i <= gt; i++) {
                int compare = comparator.compare(array[i], base);
                if (compare == 0) {
                    // 无操作
                } else if (compare < 0) {
                    swap(array, i, lt + 1);
                    lt++;
                } else {
                    swap(array, i, gt);
                    gt--;
                    i--; // 抵消掉i的自增
                }
            }
            swap(array, left, lt);
            return new int[]{lt, gt};
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
        // new QuickSort().sort1way(array, Comparator.comparing(Person::getAge));
        // new QuickSort().sort2way(array, Comparator.comparing(Person::getAge));
        new QuickSort().sort3way(array, Comparator.comparing(Person::getAge));
        System.out.println(Arrays.toString(array));

        // [[name: lily, age: 1], [name: tom, age: 2], [name: lucy, age: 4], [name: jim, age: 4], [name: white, age: 4]]
        // 对比结果可知，排序后age=4的三人顺序改变，所以快速排序不是稳定排序算法
    }

}
