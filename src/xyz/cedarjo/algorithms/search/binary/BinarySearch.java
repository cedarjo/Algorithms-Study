package xyz.cedarjo.algorithms.search.binary;

import java.util.Comparator;

/**
 * BinarySearch
 */
public class BinarySearch {

    /**
     * 递归实现二分搜索
     * 
     * @param <E>
     * @param data
     * @param e
     * @param comparator
     * @return
     */
    public <E> int searchRecursive(E[] data, E e, Comparator<E> comparator) {
        return new BinarySearchRecursive<>(comparator).search(data, 0, data.length - 1, e);
    }

    private class BinarySearchRecursive<E> {
        private Comparator<E> comparator;

        BinarySearchRecursive(Comparator<E> comparator) {
            this.comparator = comparator;
        }

        /**
         * 在[left, right]区间内查找值为e的索引
         * 
         * @param data
         * @param left
         * @param right
         * @param e
         * @return
         */
        int search(E[] data, int left, int right, E e) {
            if (left > right) {
                return -1;
            }
            int mid = (right - left) / 2 + left;
            int compare = this.comparator.compare(data[mid], e);
            if (compare == 0) {
                return mid;
            } else if (compare < 0) { // mid值更小，在[mid+1, right]查找
                return search(data, mid + 1, right, e);
            } else { // mid值更大，在[left, mid-1]查找
                return search(data, left, mid - 1, e);
            }
        }
    }

    /**
     * 非递归实现二分搜索
     * 
     * @param <E>
     * @param data
     * @param e
     * @param comparator
     * @return
     */
    public <E> int searchNonRecursive(E[] data, E e, Comparator<E> comparator) {
        int left = 0;
        int right = data.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            int compare = comparator.compare(data[mid], e);
            if (compare == 0) {
                return mid;
            } else if (compare < 0) { // mid更小，要在[mid+1, right]继续查找
                left = mid + 1;
            } else { // mid更大，要在[left, mid-1]继续查找
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 查找第一个等于指定值的元素索引
     * 
     * @param <E>
     * @param data
     * @param e
     * @param comparator
     * @return
     */
    public <E> int searchFirstEq(E[] data, E e, Comparator<E> comparator) {
        int left = 0;
        int right = data.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            int compare = comparator.compare(data[mid], e);
            if (compare == 0) {
                // 相等时，判断前一个元素和当前元素是否相等，如果相等，继续向前查找
                while (mid != 0 && comparator.compare(data[mid], data[mid - 1]) == 0) {
                    mid--;
                }
                return mid;
            } else if (compare < 0) { // mid更小，要在[mid+1, right]继续查找
                left = mid + 1;
            } else { // mid更大，要在[left, mid-1]继续查找
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个等于指定值的元素索引
     * 
     * @param <E>
     * @param data
     * @param e
     * @param comparator
     * @return
     */
    public <E> int searchLastEq(E[] data, E e, Comparator<E> comparator) {
        int left = 0;
        int right = data.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            int compare = comparator.compare(data[mid], e);
            if (compare == 0) {
                // 相等时，判断后一个元素和当前元素是否相等，如果相等，继续向后查找
                while (mid != data.length - 1 && comparator.compare(data[mid], data[mid + 1]) == 0) {
                    mid++;
                }
                return mid;
            } else if (compare < 0) { // mid更小，要在[mid+1, right]继续查找
                left = mid + 1;
            } else { // mid更大，要在[left, mid-1]继续查找
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 查找第一个大于等于指定值的元素索引
     * 
     * @param <E>
     * @param data
     * @param e
     * @param comparator
     * @return
     */
    public <E> int searchFirstGe(E[] data, E e, Comparator<E> comparator) {
        int left = 0;
        int right = data.length - 1;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            int compare = comparator.compare(data[mid], e);
            if (compare < 0) { // mid更小，要在[mid+1, right]继续查找
                left = mid + 1;
            } else { // mid更大，看mid是否为第一个大于等于e的
                // 如果mid的index是0（顶头）或者mid-1小于e，则mid是结果无疑
                if (mid == 0 || comparator.compare(data[mid - 1], e) < 0) {
                    return mid;
                }
                // 要在[left, mid-1]继续查找
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个小于等于给定值的元素索引
     * 
     * @param <E>
     * @param data
     * @param e
     * @param comparator
     * @return
     */
    public <E> int searchLastLe(E[] data, E e, Comparator<E> comparator) {
        int left = 0;
        int right = data.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            int compare = comparator.compare(data[mid], e);
            if (compare > 0) { // mid更大，要在[left, mid-1]继续查找
                right = mid - 1;
            } else { // mid更小，看mid是否为最后一个小于等于e的
                // 如果mid的index是length-1（末尾）或者mid+1大于e，则mid是结果无疑
                if (mid == data.length - 1 || comparator.compare(data[mid + 1], e) > 0) {
                    return mid;
                }
                // 要在[mid+1, right]继续查找
                left = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch handler = new BinarySearch();
        Integer[] array = { 1, 3, 5, 7, 9 };
        Comparator<Integer> comparator = Comparator.naturalOrder();
        System.out.println(handler.searchFirstGe(array, 4, comparator));
        System.out.println(handler.searchLastLe(array, 4, comparator));
    }

}