package com.company;

import java.util.Arrays;

public class StringListImpl implements StringList{

    private final int size;
    private final String[] myArrayList;

    public StringListImpl(int size) {
        this.size = size;
        myArrayList = new String[this.size];
    }

    @Override
    public String add(String item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        System.arraycopy(myArrayList, 0, myArrayList, 1, myArrayList.length - 1);
        myArrayList[0] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        indexItemCheck(index, item);
        if (myArrayList.length - 1 - index >= 0)
            System.arraycopy(myArrayList, index, myArrayList, index + 1, myArrayList.length - 1 - index);
        myArrayList[index] = item;
        return item;
    }

    @Override
    public String set(int index, String item) {
        indexItemCheck(index, item);
        myArrayList[index] = item;
        return item;
    }

    private void indexItemCheck(int index, String item) {
        if (index >= this.size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (item == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String remove(String item) {
        boolean checkItem = false;
        for (int i = myArrayList.length-1; i >= 0; i--) {
            if (myArrayList[i] != null && myArrayList[i].equals(item)) {
                checkItem = true;
                myArrayList[i] = null;
                if (myArrayList.length - 1 - i >= 0)
                    System.arraycopy(myArrayList, i + 1, myArrayList, i, myArrayList.length - 1 - i);
            }
        }
        if (!checkItem) {
            throw new IllegalArgumentException();
        }
        myArrayList[myArrayList.length - 1] = null;
        return item;
    }

    @Override
    public String remove(int index) {
        String checkItem = null;
        for (int i = myArrayList.length-1; i >= 0; i--) {
            if (myArrayList[i] != null && i == index) {
                checkItem = myArrayList[i];
                myArrayList[i] = null;
                if (myArrayList.length - 1 - i >= 0)
                    System.arraycopy(myArrayList, i + 1, myArrayList, i, myArrayList.length - 1 - i);
            }
        }
        if (checkItem == null) {
            throw new IllegalArgumentException();
        }
        myArrayList[myArrayList.length - 1] = null;
        return checkItem;
    }

    @Override
    public boolean contains(String item) {
        boolean checkItem = false;
        for (String s : myArrayList) {
            if (s != null && s.equals(item)) {
                checkItem = true;
                break;
            }
        }
        return checkItem;
    }

    @Override
    public int indexOf(String item) {
        int index = -1;
        for (int i = 0; i < myArrayList.length; i++) {
            if (myArrayList[i] != null && myArrayList[i].equals(item)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(String item) {
        int index = -1;
        for (int i = myArrayList.length-1; i >= 0; i--) {
            if (myArrayList[i] != null && myArrayList[i].equals(item)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public String get(int index) {
        if (index > myArrayList.length - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return myArrayList[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new NullPointerException();
        }
        if (this == otherList) return true;
        if (getClass() != otherList.getClass()) return false;
        StringListImpl that = (StringListImpl) otherList;
        return size == that.size && Arrays.equals(myArrayList, that.myArrayList);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        boolean checkNull = true;
        for (String s : myArrayList) {
            if (s != null) {
                checkNull = false;
                break;
            }
        }
        return checkNull;
    }

    @Override
    public void clear() {
        Arrays.fill(myArrayList, null);
    }

    @Override
    public String[] toArray() {
        String[] arr = new String[this.size];
        if (myArrayList.length - 1 >= 0) System.arraycopy(myArrayList, 0, arr, 0, myArrayList.length - 1);
        return arr;
    }

}
