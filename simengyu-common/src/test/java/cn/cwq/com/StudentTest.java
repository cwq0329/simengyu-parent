package cn.cwq.com;

import org.junit.Test;

public class StudentTest {



	@Test
	public void test3() {
		// int[] arr = { 70, 80, 31, 37, 10, 1, 48, 60, 33, 80 };
		// bubbleSort(arr);
		// selectionSort(arr);
		// int[] arr = { 11, 73, 54, 70, 18 };
		// int result = searchKey(arr, 18);
		// System.out.println("result=" + result); // result=4
		int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80, 90 };
		int result = binarySearch(arr, 30);
		System.out.println("result=" + result);

		// String re = arrToString(arr);
		// System.out.println("re=" + re);
	}

	public static int binarySearch(int[] arr, int key) {
		// TODO Auto-generated method stub
		int min = 0;
		int max = arr.length - 1;
		while (min <= max) {
			int mid = (min + max) >> 1;
			if (arr[mid] > key) {
				max = mid - 1;
			} else if (arr[mid] < key) {
				min = mid + 1;
			} else {
				return mid;
			}
		}

		return -1;

	}

	public static int searchKey(int[] arr, int key) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == key) {
				return i;
			}
		}
		return -1;
	}

	/*
	 * 选择排序 1.
	 *  0--1; 0--2; 0--3; 0--4; 0--5; 0--6; 0--7; 0--8; 0--9;
	 *  1--2;1--3; 1--4; 1--5; 1--6; 1--7; 1--8; 1--9;
	 *  2--3; 2--4; 2--5; 2--6;2--7; 2--8; 2--9; 
	 *  3--4; 3--5; 3--6; 3--7; 3--8; 3--9; 
	 *  4--5;4--6; 4--7; 4--8; 4--9;
	 *  5--6; 5--7; 5--8; 5--9;
	 *  6--7;6--8; 6--9;
	 *  7--8; 7--9; 
	 *  8--9;
	 */

	public static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					arr[i] = arr[i] ^ arr[j];
					arr[j] = arr[i] ^ arr[j];
					arr[i] = arr[i] ^ arr[j];
				}
			}
		}
	}

	public String arrToString(int[] arr) {
		String temp = "[";
		for (int i = 0; i < arr.length; i++) {
			if (i == arr.length - 1) {
				temp += arr[i] + "]";
			} else {
				temp += arr[i] + ",";
			}
		}
		return temp;
	}

	/*
	 * 冒泡排序 1. 
	 * 0--1; 1--2; 2--3; 3--4;
	 * 0--1; 1--2; 2--3;
	 * 0--1; 1--2;
	 * 0--1;
	 */

	public static void bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;

				}
			}
		}

	}
}
