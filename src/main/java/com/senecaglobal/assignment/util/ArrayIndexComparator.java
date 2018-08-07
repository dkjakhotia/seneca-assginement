package com.senecaglobal.assignment.util;


/**
* Utility class to sort data and capture the index where data is sorted . 
*
* @author  Deepak Jakhotia
* @version 1.0
* @since   2018-08-07 
*/
import java.util.Arrays;
import java.util.Comparator;

public class ArrayIndexComparator implements Comparator<Integer>
{
    private final Double[] array;

    public ArrayIndexComparator(Double[] array)
    {
        this.array = array;
    }

    public Integer[] createIndexArray()
    {
        Integer[] indexes = new Integer[array.length];
        for (int i = 0; i < array.length; i++)
        {
            indexes[i] = i; // Autoboxing
        }
        return indexes;
    }

    @Override
    public int compare(Integer index1, Integer index2)
    {
         // Autounbox from Integer to int to use as array indexes
        return array[index1].compareTo(array[index2]);
    }
    
    public static void main(String args[]) {
    	Double[] doubleInit = new Double[]{40.02, 40.02, 40.01};
    	ArrayIndexComparator comparator = new ArrayIndexComparator(doubleInit);
    	Integer[] indexes = comparator.createIndexArray();
    	Arrays.sort(indexes, comparator);
    	System.out.println(indexes.length);
    	for(Integer val : indexes) {
    		System.out.println(val);
    	}
    }
}
