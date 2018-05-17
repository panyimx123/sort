package com.y2pan.practice.sort;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    private static int[] unSort = {23, 6, 84, 34, 13, 32, 54, 2, 3, 4};

    /**
     * worst  avg     best
     * O(n^2) O(n^2)  O(n)
     */
    private static int[] maoPaoSort(int[] array) {
        int id = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] < array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
                //System.out.print(++id + " ");
                //print(array);
            }
        }
        return array;
    }

    /**
     * worst  avg     best
     * O(n^2) O(n^2)  O(n)
     */
    private static int[] maoPaoSort1(int[] array) {
        int id = 0;
        for (int i = 0; i < array.length; i++) {
            boolean flag = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] < array[j + 1]) {
                    flag = true;
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
                //System.out.print(++id + " ");
                //print(array);
            }
            if (!flag) {
                break;
            }
        }
        return array;
    }

    /**
     * worst  avg     best
     * O(n^2) O(n^2)  O(n)
     */
    private static int[] maoPaoSort2(int[] array) {
        int id = 0;
        int k = 0;
        int flag = array.length - 1;
        for (int i = 0; i < array.length && flag != 0; i++) {
            k = flag;
            flag = 0;
            for (int j = 0; j < k; j++) {
                if (array[j] < array[j + 1]) {
                    flag = j;
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
                //System.out.print(++id + " ");
                //print(array);
            }
        }
        return array;
    }

    /**
     * worst  avg     best
     * O(n^2) O(n^2)  O(n)
     */
    private static int[] insertSort(int[] array) {
        int j;
        int id = 0;
        for (int p = 1; p < array.length; p++) {
            int tmp = array[p];
            for (j = p; j > 0 && tmp > array[j - 1]; j--) {
                array[j] = array[j - 1];
                //System.out.print(++id + " ");
                //print(array);
            }
            array[j] = tmp;
        }
        return array;
    }

    /**
     * worst  avg       best
     * O(n^2) O(n^1.3)  O(n)
     */
    private static int[] shellSort(int[] array) {
        int j;
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            //System.out.println(gap);
            for (int i = gap; i < array.length; i++) {
                int tmp = array[i];
                for (j = i; j >= gap && tmp > array[j - gap]; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = tmp;
                //print(array);
            }
        }
        return array;
    }

    /**
     * worst  avg     best
     * O(n^2) O(n^2)  O(n)
     */
    private static int[] selectSort(int[] array) {
        int temp;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    private static void percDown(int[] array, int i, int n) {
        int child = 0;
        int tmp;
        for (tmp = array[i]; leftChild(i) < n; i = child) {
            child = leftChild(i);
            if (child != n - 1 && array[child] < array[child + 1]) {
                child++;
            }
            if (tmp < array[child]) {
                array[i] = array[child];
            } else {
                break;
            }
        }
        array[i] = tmp;
    }

    /**
     * worst      avg         best
     * O(N*log2N) O(N*log2N)  O(N*log2N)
     */
    private static int[] heapSort(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            percDown(array, i, array.length);
        }
        for (int i = array.length - 1; i > 0; i--) {
            int tmp = array[0];
            array[0] = array[i];
            array[i] = tmp;
            percDown(array, 0, i);
        }
        return array;
    }

    private static void merge(int[] array, int[] tmpArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (array[leftPos] < array[rightPos]) {
                tmpArray[tmpPos++] = array[leftPos++];
            } else {
                tmpArray[tmpPos++] = array[rightPos++];
            }
        }
        while (leftPos <= leftEnd) {
            tmpArray[tmpPos++] = array[leftPos++];
        }

        while (rightPos <= rightEnd) {
            tmpArray[tmpPos++] = array[rightPos++];
        }

        for (int i = 0; i < numElements; i++, rightEnd--) {
            array[rightEnd] = tmpArray[rightEnd];
        }
    }

    private static void mergeSort(int[] array, int[] tmpArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(array, tmpArray, left, center);
            mergeSort(array, tmpArray, center + 1, right);
            merge(array, tmpArray, left, center + 1, right);
        }
    }

    /**
     * worst      avg         best
     * O(N*log2N) O(N*log2N)  O(N*log2N)
     */
    private static int[] mergeSort(int[] array) {
        int[] tmpArray = new int[array.length];
        mergeSort(array, tmpArray, 0, array.length - 1);
        return array;
    }

    /**
     * worst      avg         best
     * O(N*log2N) O(N*log2N)  O(N*log2N)
     */
    private static int[] quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private static void quickSort(int[] array, int left, int right) {
        if (left + 2 <= right) {
            int pivot = median3(array, left, right);
            int i = left + 1, j = right - 2;
            for (; ; ) {
                while (array[i] < pivot) {
                    i++;
                }
                while (array[j] > pivot) {
                    j--;
                }
                if (i < j) {
                    swap(array, i, j);
                } else {
                    break;
                }
            }

            swap(array, i, right - 1);
            quickSort(array, left, i - 1);
            quickSort(array, i + 1, right);
        } else {
            insectionSort(array, left, right);
        }
    }

    private static void insectionSort(int[] array, int left, int right) {
        for (int i = left; i < right; i++) {
            for (int j = i, tmp = array[i + 1]; j >= 0 && tmp < array[j]; j--) {
                array[i + 1] = array[j];
                array[j] = tmp;
            }
        }
    }

    private static int median3(int[] array, int left, int right) {
        int center = (left + right) / 2;
        if (array[center] < array[left]) {
            swap(array, center, left);
        }
        if (array[right] < array[left]) {
            swap(array, left, right);
        }
        if (array[right] < array[center]) {
            swap(array, right, center);
        }
        swap(array, center, right - 1);
        return array[right - 1];
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private static void print(int[] array) {
        System.out.println(Arrays.stream(array).mapToObj(String::valueOf)
                .collect(Collectors.joining(",")));
    }

    private static void runSort(Function<int[], int[]> sort, String sortname) {
        System.out.print("before sort:");
        print(unSort);
        System.out.println("sort func name:" + sortname);
        int[] result = sort.apply(unSort.clone());
        System.out.print("after sort:");
        print(result);
        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) {
        runSort(Main::maoPaoSort, "maoPaoSort");
        runSort(Main::maoPaoSort1, "maoPaoSort1");
        runSort(Main::maoPaoSort2, "maoPaoSort2");
        runSort(Main::insertSort, "insertSort");
        runSort(Main::shellSort, "shellSort");
        runSort(Main::selectSort, "selectSort");
        runSort(Main::heapSort, "heapSort");
        runSort(Main::mergeSort, "mergeSort");
        runSort(Main::quickSort, "quickSort");
    }
}
