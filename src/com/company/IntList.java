package com.company;

public interface IntList {

    Integer add(Integer item);

    Integer add(int index, Integer item);

    Integer set(int index, Integer item);

    Integer remove(Integer item);

    Integer remove(int index);

    boolean contains(Integer item);

    Integer indexOf(Integer item);

    Integer lastIndexOf(Integer item);

    Integer get(int index);

    boolean equals(IntList otherList);

    Integer size();

    boolean isEmpty();

    void clear();

    Integer[] toArray();
}
