package xyz.cedarjo.algorithms.sort.extension;

import java.util.Comparator;
import java.util.Random;

public class TheNLargestElement<E> {

    private Comparator<E> comparator;
    private Random random;

    public TheNLargestElement(Comparator<E> comparator) {
        this.comparator = comparator;
        this.random = new Random();
    }

    public E nLargestElement(E[] array, int n) {
        if (n <= 0 || n > array.length) {
            throw new IllegalArgumentException("越界");
        }
        int partitionPos = partition(array, 0, array.length - 1);
        // partitionPos存放着第(partitionPos+1)大元素
        while (partitionPos + 1 != n) {
            if (partitionPos + 1 > n) {
                // 要找第5大元素，现找到第8大元素；
                // 已知找到元素前的7个元素都更大，之后的元素都更小
                // 那第5大元素一定在前边
                partitionPos = partition(array, 0, partitionPos - 1);
            } else {
                partitionPos = partition(array, partitionPos + 1, array.length - 1);
            }
        }
        return array[partitionPos];
    }

    private int partition(E[] array, int left, int right) {
        // 降序排列
        int r = random.nextInt(right - left + 1) + left;
        swap(array, left, r);
        // left为基准元素, [left+1, partition]均>=基准, (partition, right]均<基准
        int partition = left;
        E base = array[left];
        for (int i = left + 1; i <= right; i++) {
            int compare = this.comparator.compare(array[i], base);
            if (compare >= 0) {
                partition++;
                swap(array, partition, i);
            }
        }
        swap(array, left, partition);
        return partition;
    }

    private void swap(E[] array, int posA, int posB) {
        E temp = array[posA];
        array[posA] = array[posB];
        array[posB] = temp;
    }

    public static void main(String[] args) {
        TheNLargestElement<Integer> handler = new TheNLargestElement<Integer>(Comparator.naturalOrder());
        Integer rst = handler.nLargestElement(new Integer[] { 3, 2, 7, 5, 1, 9, 4, 8, 6 }, 6);
        System.out.println(rst);
    }

}