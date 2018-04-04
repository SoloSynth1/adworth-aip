package io.adworth.aip.helper;

import java.util.ArrayList;

public class Sort {
	public static ArrayList<Integer> mergeSort(ArrayList<Integer> arrl) {
		int temp_arr[] = new int[arrl.size()];
		for (int i = 0; i<arrl.size();i++) {
			temp_arr[i] = arrl.get(i);
		}
		mergeSort(arrl, temp_arr, 0, arrl.size()-1);
		return arrl;
	}
	
	public static void mergeSort(ArrayList<Integer> arrl, int[] temp_arr, int low, int high) {
		if (high <= low) {
			return;
		} else {
			int mid = (high+low) / 2;
			mergeSort(arrl, temp_arr, low, mid);
			mergeSort(arrl, temp_arr, mid+1, high);
			merge(arrl, temp_arr, low, mid, high);
		}
	}
	
	public static void merge(ArrayList<Integer> arrl, int[] temp_arr, int low, int mid, int high) {
		int left = low;
		int right = mid+1;
		int arrayIndex = left;
		while (left <= mid && right <= high) {
			if (temp_arr[left] <= temp_arr[right]) {
				arrl.set(arrayIndex, temp_arr[left]);
				left++;
			} else{
				arrl.set(arrayIndex, temp_arr[right]);
				right++;
			}
			arrayIndex++;
		}
		while (left <= mid) {
			arrl.set(arrayIndex, temp_arr[left]);
			left++;
			arrayIndex++;
		}
		while (right <= high) {
			arrl.set(arrayIndex, temp_arr[right]);
			right++;
			arrayIndex++;
		}
		for (int i = low; i <= high ; i++){
			temp_arr[i] = arrl.get(i);
		}
	}
}