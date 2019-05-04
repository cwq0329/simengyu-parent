package cn.cwq.com;

import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;

public class InterviewTest {

	@Test
	public void test1() {
		System.out.println(Str(new int[] { 3, 4, 5, 6, 7, 8 }));
		int re = getSum(1, 10);
		System.out.println(re + "getSumgetSumgetSum");
		// 1. 从一个整型数组之中找出其中的最大值.
		int[] arrm = new int[] { 10, 22, 89, 88, 25, 77 };
		System.out.println(getMax(arrm));

		// 数组转字符串输出
		int[] arr1 = new int[] { 10, 20, 30, 40, 50 };
		System.out.println(arraysToString(arr1));
		System.out.println(arraysToString1(arr1) + "stringBuffer");

		// 选择排序
		int[] arr2 = { 70, 80, 31, 37, 10, 1, 48, 60, 33, 80 };
		selectionSort(arr2);
		String res = arraysToString(arr2);
		System.out.println(res);

		// 冒泡排序
		// 12, 35, 99, 18, 76
		int[] arr3 = new int[] { 12, 35, 99, 18, 76 };
		bbSort(arr3);
		String res1 = arraysToString(arr3);
		System.out.println(res1);

		// 无需查找
		int[] arr4 = { 11, 73, 54, 70, 18 };
		System.out.println(searchKey(arr4, 18) + "-----------search");

		// 折半查找
		int[] arr5 = { 10, 20, 30, 40, 50, 60, 70, 80, 90 };
		System.out.println(binarySearch(arr5, 80) + "===========binarySearch");

		// s数组反转
		int[] arr6 = { 12, 23, 34, 45, 56 };
		reverse(arr6);
		System.out.println(arraysToString1(arr6) + "ssssssssssssssss");
	}

	public static void reverse(int[] arr6) {
		for (int start = 0, end = arr6.length - 1; start < end; start++, end--) {
			int temp = arr6[start];
			arr6[start] = arr6[end];
			arr6[end] = temp;
		}
		// TODO Auto-generated method stub

	}

	public static int binarySearch(int[] arr5, int key) {
		int min = 0;
		int max = arr5.length - 1;

		while (min <= max) {
			int mid = (min + max) >> 1;
			if (arr5[mid] > key) {
				max = mid - 1;
			} else if (arr5[mid] < key) {
				min = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}

	static int searchKey(int[] arr4, int key) {
		for (int j = 0; j < arr4.length; j++) {
			if (arr4[j] == key) {
				return j;
			}
		}
		return -1;
	}

	public static String arraysToString1(int[] arr1) {
		StringBuffer buffer = new StringBuffer("[");
		for (int i = 0; i < arr1.length - 1; i++) {
			buffer.append(arr1[i]).append(",");
		}
		buffer.append(arr1[arr1.length - 1]).append("]");
		return buffer.toString();
	}

	public static void bbSort(int[] arr3) {
		for (int i = 0; i < arr3.length; i++) {
			for (int j = 0; j < arr3.length - i - 1; j++) {
				if (arr3[j] < arr3[j + 1]) {
					int temp = arr3[j];
					arr3[j] = arr3[j + 1];
					arr3[j + 1] = temp;
				}
			}
		}

	}

	public static void selectionSort(int[] arr2) {
		for (int i = 0; i < arr2.length - 1; i++) {
			for (int j = i + 1; j < arr2.length; j++) {
				if (arr2[i] > arr2[j]) {
					arr2[i] = arr2[i] ^ arr2[j];
					arr2[j] = arr2[i] ^ arr2[j];
					arr2[i] = arr2[i] ^ arr2[j];

				}
			}
		}

	}

	public static String arraysToString(int[] arr1) {
		String temp = "[";
		for (int i = 0; i < arr1.length; i++) {
			if (i == arr1.length - 1) {
				temp = temp + arr1[i] + "]";
			} else {
				temp = temp + arr1[i] + ",";
			}

		}
		return temp;
	}

	public static int getMax(int[] arrm) {
		int max = arrm[0];
		for (int i = 1; i < arrm.length; i++) {
			if (max < arrm[i]) {
				max = arrm[i];
			}
		}
		return max;

	}

	public static String Str(int[] is) {
		String st = Arrays.toString(is);

		return st.substring(1, st.length() - 1);
	}

	public static String Str1(int[] arr) {
		StringBuffer stb = new StringBuffer();

		for (int i = 0; i < arr.length; i++) {
			stb.append(",").append(arr[i]); // ,1,2,3,4
		}
		return stb.length() > 0 ? stb.substring(1) : "";
	}

	public static int getSum(int a, int b) {
		// int count = 0;
		int sum = 0;
		for (int i = Math.min(a, b); i <= Math.max(a, b); i++) {
			if (i % 2 == 0) {
				sum += i;
			}

		}
		return sum;

	}

	@Test
	public void test1111() {
		int i = 5;
		int result = -1;
		switch (i) {
		default:
			result = 0;
		case 1:

			result = 1;
			break;
		case 5:
			result = 5;
		case 2:
			result = 2;
			// break;
		case 3:
			result = 3;

		}
		System.out.println(result);
	}

	@Test
	public void odevityTest() {
		odevity();
	}

	public static void odevity() {
		// 1到100循环将奇数放到一个int[50],把偶数存放到hashmap，map的key从7开始递增
		int[] numbers = new int[50];
		int hashKey = 7;
		HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
		for (int i = 1, j = numbers[i] == 0 ? 1 : numbers[i]; i < 50 && j < 101; j++) {
			if (j % 2 == 1) {
				numbers[i] = j;
				i++;
				System.out.println("奇数: " + j);
			} else {
				hashMap.put(hashKey, j);
				hashKey++;
				System.out.println("偶数为： " + j);
			}

		}
		for (Integer j : hashMap.keySet()) {
			System.out.println(j + " == " + hashMap.get(j));
		}
	}
}
