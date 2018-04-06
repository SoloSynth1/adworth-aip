package io.adworth.aip.helper;

import java.util.ArrayList;

public class Sort {
	
	public static ArrayList<Integer> mergeSort(ArrayList<Integer> arrl) {
		Integer tempArray[] = arrl.toArray(new Integer[arrl.size()]);
		mergeSort(arrl, tempArray, 0, arrl.size()-1);
		return arrl;
	}
	
	public static void mergeSort(ArrayList<Integer> arrl, Integer[] tempArray, int low, int high) {
		if (high > low) {
			int mid = (high+low) / 2;
			mergeSort(arrl, tempArray, low, mid);
			mergeSort(arrl, tempArray, mid+1, high);
			merge(arrl, tempArray, low, mid, high);
		}
	}
	
	public static void merge(ArrayList<Integer> arrl, Integer[] tempArray, int low, int mid, int high) {
		int left = low;
		int right = mid+1;
		int arrayIndex = left;
		while (left <= mid && right <= high) {
			if (tempArray[left] <= tempArray[right]) {
				arrl.set(arrayIndex, tempArray[left]);
				left++;
			} else{
				arrl.set(arrayIndex, tempArray[right]);
				right++;
			}
			arrayIndex++;
		}
		while (left <= mid) {
			arrl.set(arrayIndex, tempArray[left]);
			left++;
			arrayIndex++;
		}
		while (right <= high) {
			arrl.set(arrayIndex, tempArray[right]);
			right++;
			arrayIndex++;
		}
		for (int i = low; i <= high ; i++){
			tempArray[i] = arrl.get(i);
		}
	}
}