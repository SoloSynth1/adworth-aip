package io.adworth.aip;

import static org.junit.Assert.assertArrayEquals;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.adworth.aip.helper.Sort;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SortTest {
	
	private int arraySize = 1000000;

	@Test
	public void shouldSortARandomlyGeneratedArray() {
		ArrayList<Integer> Array = generateRandList(arraySize);
		ArrayList<Integer> refArray = new ArrayList<Integer>(Array);
		
		Sort.mergeSort(Array);
		Collections.sort(refArray);
		assertArrayEquals(Array.toArray(), refArray.toArray());
	}
	
	@Test
	public void shouldSortAnAllZeroArray() {
		ArrayList<Integer> Array = new ArrayList<Integer>();
		for (int i = 0; i< arraySize; i++) {
			Array.add(0);
		}
		ArrayList<Integer> refArray = new ArrayList<Integer>(Array);
		
		Sort.mergeSort(Array);
		Collections.sort(refArray);
		assertArrayEquals(Array.toArray(), refArray.toArray());
	}
	
	@Test
	public void shouldSortAZeroLengthArray() {
		ArrayList<Integer> Array = new ArrayList<Integer>();
		ArrayList<Integer> refArray = new ArrayList<Integer>(Array);
		
		Sort.mergeSort(Array);
		Collections.sort(refArray);
		assertArrayEquals(Array.toArray(), refArray.toArray());
	}
	
	@Test
	public void shouldSortArrayWithInf() {
		ArrayList<Integer> Array = generateRandList(arraySize);
		Integer[] speicalIntegers = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
		SecureRandom rand = new SecureRandom();
		for (Integer value : speicalIntegers) {
			Array.set(rand.nextInt(Array.size()-1), value);
		}
		ArrayList<Integer> refArray = new ArrayList<Integer>(Array);
		Sort.mergeSort(Array);
		Collections.sort(refArray);
		assertArrayEquals(Array.toArray(), refArray.toArray());
	}
	
	public ArrayList<Integer> generateRandList(int size){
		SecureRandom rand = new SecureRandom();
		ArrayList<Integer> randArray = new ArrayList<Integer>();
		for (int i = 0; i < size ; i++) {
			randArray.add(rand.nextInt());
		}
		return randArray;
	}
}
