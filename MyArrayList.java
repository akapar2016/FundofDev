/*
 * Author:  Aayush Kapar, akapar2016@my.fit.edu
 * Course:  CSE 1002, Section 01, Fall 2017
 * Project: 18 MyArrayList
 */

//package labs;
public final class MyArrayList {

    private static final int CAPACITY = 10;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TESTADD = 100;
    private static final int TEST = 9;
    private Integer[] items;
    private int size;

    //Increases the capacity of the array,
    //if necessary, to ensure that it can hold at least
    //the number of elements specified by the argument
    private void ensureCapacity (final int minCapacity) {
        final Integer[] tempItems = new Integer[minCapacity];
        for (int count = ZERO; count < minCapacity; count++) {
            tempItems[count] = items[count];
        }
        items = new Integer[size + CAPACITY];
        for (int count = ZERO; count < minCapacity; count++) {
            items[count] = tempItems[count];
        }
    }

    //Constructs an empty list with an initial capacity of 10
    public MyArrayList () {
        items = new Integer[CAPACITY];
        size = ZERO;
    }

    //Constructs an empty list with the specified initial capacity
    public MyArrayList (final int initialCapacity) {
        if (initialCapacity < ZERO) {
            throw new IllegalArgumentException();
        }
        items = new Integer[initialCapacity];
        size = ZERO;
    }

    //Inserts the specified element at the end of the list,
    //return true if it succeeded
    public boolean add (final Integer element) {
        if (size == items.length) {
            ensureCapacity (size);
        }
        items[size] = element;
        size++;
        return true;
    }

    //Returns the element at the specified position in this list
    public Integer get (final int index) {
        if (index < ZERO || index > size) {
            throw new IndexOutOfBoundsException();
        }
        return items[index];
    }



    //Replaces the element at the specified position in this list,
    //should return the removed element value
    public Integer set (final int index, final Integer element) {
        if (index < ZERO || index > size) {
            throw new IndexOutOfBoundsException();
        }
        final Integer ret = items[index];
        items[index] = element;
        return ret;
    }

    //Removes the element at index specified by the argument,
    //return the removed element value.
    public Integer remove (final int index) {
        if (index < ZERO || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Integer ret = null;
        if (index == size) {
            ret = items[index];
            items[index] = null;
        } else {
            ret = items[index];
            for (int count = index; count < size - ONE; count++) {
                items[count] = items[count + ONE];
            }
            items[size - ONE] = null;
            size--;
        }
        return ret;

    }

    //Find and Remove the first occurrence
    //of the specified element from this list, if it is present.
    public boolean remove (final Integer element) {
        boolean isThere = false;
        int index = ZERO;
        for (int count = size - ONE; count >= 0; count--) {
            if (items[count] == element) {
                isThere = true;
                index = count;
            }
        }
        if (!isThere) {
            return false;
        } else if (index == size) {
            items[index] = null;
        } else {
            for (int count = index; count < size - ONE; count++) {
                items[count] = items[count + 1];
            }
            items[size - ONE] = null;
            size--;
        }
        return true;

    }

    //Returns the number of elements in this list
    public int size () {
        return size;
    }
    //Removes all of the elements from this list.
    public void clear () {
        items = new Integer[CAPACITY];
        size = ZERO;
    }

    public static void main (final String[] args) {
        final Integer temp = TEST;
        final MyArrayList test = new MyArrayList();
        for (int count = ZERO; count < TESTADD; count++) {
            test.add(count);
        }

        for (int count = ZERO; count < TESTADD; count++) {
            System.out.println(test.get(count));
        }
        System.out.println(test.size);
        System.out.println(test.remove(TEST));
        System.out.println(test.size);
        test.add(temp);
        //System.out.println(test.get(ZERO));


    }

}
