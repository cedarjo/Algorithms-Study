package xyz.cedarjo.algorithms.sort.extension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class InversionPair<E> {

    private Comparator<E> comparator;

    public InversionPair(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public List<List<E>> inversionPair(E[] array) {
        List<List<E>> rst = new ArrayList<>();
        inversionPair(array, 0, array.length - 1, rst);
        return rst;
    }

    private void inversionPair(E[] array, int left, int right, List<List<E>> rst) {
        if (left >= right) {
            return;
        }
        int mid = (right - left) / 2 + left;
        inversionPair(array, left, mid, rst);
        inversionPair(array, mid + 1, right, rst);

        // 求[left, mid]与[mid+1, right]的逆序对
        merge(array, left, mid, right, rst);
    }

    private void merge(E[] array, int left, int mid, int right, List<List<E>> rst) {
        E[] temp = (E[]) new Object[right - left + 1];
        int leftPos = left;
        int rightPos = mid + 1;
        int tempPos = 0;
        while (leftPos <= mid && rightPos <= right) {
            if (this.comparator.compare(array[leftPos], array[rightPos]) <= 0) {
                temp[tempPos++] = array[leftPos++];
            } else {
                for (int i = leftPos; i <= mid; i++) {
                    List<E> item = new ArrayList<>(2);
                    item.add(array[i]);
                    item.add(array[rightPos]);
                    rst.add(item);
                }
                temp[tempPos++] = array[rightPos++];
            }
        }
        while (leftPos <= mid) {
            temp[tempPos++] = array[leftPos++];
        }
        while (rightPos <= right) {
            temp[tempPos++] = array[rightPos++];
        }
        for (int i = 0; i < temp.length; i++) {
            array[left + i] = temp[i];
        }
    }

    public static void main(String[] args) {
        InversionPair<Integer> inversionPair = new InversionPair<Integer>(Comparator.naturalOrder());
        List<List<Integer>> rst = inversionPair.inversionPair(new Integer[] { 3, 2, 7, 5, 1, 9, 4, 8, 6 });
        System.out.println(rst.size());
        for (List<Integer> var : rst) {
            System.out.println(var);
        }

    }

}