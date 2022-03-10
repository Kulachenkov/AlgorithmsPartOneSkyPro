package com.company;

import java.util.Arrays;

public class IntListImpl implements IntList{

    private int size;
    private final Integer [] myArrayList;
    private final static int DEFAULT_CAPACITY = 10;

    public IntListImpl() {
        myArrayList = new Integer[DEFAULT_CAPACITY];
    }

    @Override
    public Integer add(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size == myArrayList.length) {
            expandArray();
        }
        myArrayList[size++] = item;
        return item;
    }

    private void expandArray() {
        int newCapacity = myArrayList.length * 2;
        Arrays.copyOf(myArrayList, newCapacity);
    }

    @Override
    public Integer add(int index, Integer item) {
        indexItemCheck(index, item);
        if (size == myArrayList.length) {
            expandArray();
        }
            System.arraycopy(myArrayList, index, myArrayList, index + 1, size - index);
        myArrayList[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        indexItemCheck(index, item);
        myArrayList[index] = item;
        return item;
    }

    private void indexItemCheck(int index, Integer item) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Integer remove(Integer item) {
        checkNull(item);
        int removingElementIndex = indexOf(item);
        if (removingElementIndex == -1) {
            throw new IllegalArgumentException();
        }
        System.arraycopy(myArrayList, removingElementIndex+1, myArrayList, removingElementIndex, size - removingElementIndex);
        size--;
        return item;
    }

    private void checkNull(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Integer remove(int index) {
        int item = myArrayList[index];
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        System.arraycopy(myArrayList, index, myArrayList, index + 1, size - index);
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        checkNull(item);
        Integer[] myArrayListCopy = myArrayList.clone();
        sortSelection(myArrayListCopy);
        return binarySearch(myArrayListCopy,item) != -1;
    }

    private Integer binarySearch(Integer[] arr, Integer item) {
        int min = 0;
        int max = size - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(arr[mid])) {
                return mid;
            }
            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return - 1;
    }

    private void sortSelection(Integer[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    private static void swapElements(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }

    @Override
    public Integer indexOf(Integer item) {
        checkNull(item);
        for (int i = 0; i <= size-1; i++) {
            if (myArrayList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer lastIndexOf(Integer item) {
        checkNull(item);
        for (int i = size-1; i >= 0; i--) {
            if (myArrayList[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return myArrayList[index];
    }

    @Override
    public boolean equals(IntList otherList) {
        if (otherList == null || size != otherList.size()) {
            return false;
        }
        for (int i = 0; i <= size-1; i++) {
            if (!get(i).equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Integer size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(myArrayList, null);
    }

    @Override
    public Integer[] toArray() {
        Integer[] arr = new Integer[this.size];
        if (myArrayList.length - 1 >= 0) System.arraycopy(myArrayList, 0, arr, 0, myArrayList.length - 1);
        return arr;
    }

}
