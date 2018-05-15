package com.y2pan.practice.sort;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    private static int[] unSort = {23, 6, 84, 34, 13, 32, 54, 12, 3, 4};

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

    private static void print(int[] array) {
        System.out.println(Arrays.stream(array).mapToObj(String::valueOf)
                .collect(Collectors.joining(",")));
    }

    private static void runSort(Function<int[], int[]> sort, String sortname) {
        System.out.println("********************");
        System.out.print("before sort:");
        print(unSort);
        System.out.println("sort func name:" + sortname);
        int[] result = sort.apply(unSort.clone());
        System.out.print("after sort:");
        print(result);
        System.out.println("********************");
    }

    public static void main(String[] args) {
        runSort(Main::maoPaoSort,"maoPaoSort");
        runSort(Main::maoPaoSort1,"maoPaoSort1");
        runSort(Main::maoPaoSort2,"maoPaoSort2");
        runSort(Main::insertSort,"insertSort");
    }
}
