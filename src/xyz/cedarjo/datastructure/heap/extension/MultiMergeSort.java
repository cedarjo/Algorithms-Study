package xyz.cedarjo.datastructure.heap.extension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class MultiMergeSort<E> {

    private int way;

    private Comparator<E> comparator;

    public MultiMergeSort(int way, Comparator<E> comparator) {
        if (way < 2) {
            way = 2;
        }
        this.way = way;
        this.comparator = comparator;
    }

    public void sort(E[] array) {
        // 将array进行way等分
        // 针对array.length/way == 1的情况，这里将way置为2
        if (array.length <= 1) {
            return;
        }
        if (array.length / way == 1) {
            new Handler(2).sort(array);
        } else {
            new Handler(way).sort(array);
        }
    }

    class Handler {
        private int way;

        Handler(int way) {
            this.way = way;
        }

        public void sort(E[] array) {
            int length = array.length;
            List<Integer> capacityList = new ArrayList<>();
            split(length, way, capacityList);
            int pos = 0;
            Map<Integer, Integer> posAndIndex = new HashMap<>();
            List<List<Integer>> areas = new ArrayList<>();
            for (int i = 0; i < capacityList.size(); i++) {
                Integer capacity = capacityList.get(i);
                posAndIndex.put(pos, i);
                areas.add(Arrays.asList(pos, pos + capacity - 1));
                sort(array, pos, pos + capacity - 1);
                pos += capacity;
            }
            // way路归并
            E[] copyArray = Arrays.copyOf(array, array.length);
            PriorityQueue<Integer> heap = new PriorityQueue<>((posA, posB) -> {
                return comparator.compare(copyArray[posA], copyArray[posB]);
            });
            areas.forEach((area) -> {
                heap.add(area.get(0));
                area.set(0, area.get(0) + 1);
            });

            int tempPos = 0;
            E[] temp = (E[]) new Object[length];
            while (!heap.isEmpty()) {
                pos = heap.poll();
                int index = posAndIndex.get(pos);
                temp[tempPos++] = array[pos];
                List<Integer> area = areas.get(index);
                if (area.get(1) >= area.get(0)) {
                    heap.add(area.get(0));
                    posAndIndex.put(area.get(0), index);
                    posAndIndex.remove(pos);
                    area.set(0, area.get(0) + 1);
                }
            }
            for (int i = 0; i < temp.length; i++) {
                array[i] = temp[i];
            }
        }

        private void sort(E[] array, int left, int right) {
            if (left >= right) {
                return;
            }
            int mid = (right - left) / 2 + left;
            sort(array, left, mid);
            sort(array, mid + 1, right);
            merge(array, left, mid, right);
        }

        private void merge(E[] array, int left, int mid, int right) {
            E[] temp = (E[]) new Object[right - left + 1];
            int leftPos = left;
            int rightPos = mid + 1;
            int tempPos = 0;
            while (leftPos <= mid && rightPos <= right) {
                if (comparator.compare(array[leftPos], array[rightPos]) <= 0) {
                    temp[tempPos++] = array[leftPos++];
                } else {
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

        private void split(int length, int slice, List<Integer> capacityList) {
            if (slice == 0) {
                return;
            }
            if (length % slice == 0) {
                for (int i = slice; i > 0; i--) {
                    capacityList.add(length / slice);
                }
                return;
            }
            int capacity = length / slice + 1;
            capacityList.add(capacity);
            length -= capacity;
            slice--;
            split(length, slice, capacityList);
        }

    }

    public static void main(String[] args) {
        MultiMergeSort<Integer> handler = new MultiMergeSort<Integer>(4, Comparator.naturalOrder());
        Integer[] array = { 1, 3, 5, 7, 16, 14, 12, 10, 9, 0, 8, 6, 4 };
        handler.sort(array);
        System.out.println(Arrays.toString(array));
    }

}