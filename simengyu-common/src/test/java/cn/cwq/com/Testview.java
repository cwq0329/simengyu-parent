package cn.cwq.com;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import org.junit.Test;

public class Testview {
    
    @Test
    public void t1(){
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
    
    @Test
    public void t2(){
        int result =osum(1,10);
        System.out.println(result);
    }

    public  int osum(int i, int j) {
        int sum=0;
        for (int k=Math.min(i, j);k<=Math.max(i, j);k++) {
            if(k % 2==0){
                sum+=k;
            }
        }
        
        return sum;
    }
    
    
    @Test
    public void t3(){
       int[] arr={3,4,5,6,7,8};
       String result =arrTostr(arr);
       System.out.println(result);
       System.out.println(Arrays.asList(arr));
       
    }
    
    public  String arrTostr(int[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]).append(",");
        
    }
        return sb.length()>0?sb.toString().substring(0, sb.length()-1):"";
       
    }
    
    @Test
    public void t4(){
     // 1. 从一个整型数组之中找出其中的最大值.
        int[] arrm = new int[] { 10, 22, 89, 88, 25, 77 };
        int max = getMax(arrm);
        System.out.println(max);
       
    }

    public int getMax(int[] arrm) {
        int max=arrm[0];
        for (int i = 0; i < arrm.length; i++) {
            if(max<arrm[i]){
                max=arrm[i];
            }
        }
        return max;
    }
    
 
  
    @Test
    public void t5(){
     // 数组转字符串输出
        int[] arr1 = new int[] { 10, 20, 30, 40, 50 };
        System.out.println(arraysToString(arr1));
        System.out.println(arraysToString1(arr1) + "stringBuffer");
       
    }

    private String arraysToString1(int[] arr1) {
       StringBuffer sb = new StringBuffer("[");
       for (int i = 0; i < arr1.length-1; i++) {
        sb.append(arr1[i]).append(",");
    }
       sb.append(arr1[arr1.length-1]).append("]");
        return sb.toString();
    }

    private String arraysToString(int[] arr1) {
        String temp="[";
        for (int i = 0; i < arr1.length; i++) {
          if(i==arr1.length-1){
              temp+= arr1[i]+"]";
          }else{
              temp+=arr1[i]+",";
          }
        }
        return temp;
    }
    
    
    
    
    
    @Test
    public void t6(){
     // 选择排序 从小到大
        int[] arr2 = { 70, 80, 31, 37, 10, 1, 48, 60, 33, 80 };
      selectionSort(arr2);
        String res = arraysToString(arr2);
        System.out.println(res);
       
    }

    private void selectionSort(int[] arr2) {
        for (int i = 0; i < arr2.length-1; i++) {
            for (int j = i+1; j < arr2.length; j++) {
                if(arr2[i]>arr2[j]){
                    arr2[i]=arr2[i] ^ arr2[j];
                    arr2[j]=arr2[i] ^ arr2[j];
                    arr2[i]=arr2[i] ^ arr2[j];
                }
            }
        }
    }
    
    
    @Test
    public void t7(){
        // 冒泡排序
        // 12, 35, 99, 18, 76
        int[] arr3 = new int[] { 12, 35, 99, 18, 76 };
        bbSort(arr3);
        String res1 = arraysToString(arr3);
        System.out.println(res1);
       
    }

    private void bbSort(int[] arr3) {
       for (int i = 0; i < arr3.length-1; i++) {
        for (int j = 0; j < arr3.length-i-1; j++) {
            if(arr3[j]>arr3[j+1]){
                int temp=arr3[j];
                arr3[j]=arr3[j+1];
                arr3[j+1]=temp;
            }
        }
    }
        
    }
    
    
    @Test
    public void t8(){
     // 无需查找
        int[] arr4 = { 11, 73, 54, 70, 18 };
        System.out.println(searchKey(arr4, 18) + "-----------search");
        
       
    }

    private int searchKey(int[] arr4, int i) {
        for (int j = 0; j < arr4.length; j++) {
            if(arr4[j]==i){
                return j;
            }
        }
        return -1;
    }
 
    
    @Test
    public void t9(){
     // 折半查找
        int[] arr5 = { 10, 20, 30, 40, 50, 60, 70, 80, 90 };
        System.out.println(binarySearch(arr5, 80) + "===========binarySearch");
        
       
    }

    private int binarySearch(int[] arr5, int i) {
       int min=0;
       int max=arr5.length-1;
       while(min<=max){
           int mid=(min+max)>>1;
       if(arr5[mid]>i){
           max=mid-1;
       }else if(arr5[mid]<i){
           min =mid+1;
       }else{
           return mid;
       }
       }
        return -1;
    }
 
    @Test
    public void t10(){
     // s数组反转
        int[] arr6 = { 12, 23, 34, 45, 56 };
        reverse(arr6);
        System.out.println(arraysToString1(arr6) + "ssssssssssssssss");
        
       
    }

    private void reverse(int[] arr6) {
        for (int start=0,end=arr6.length-1;start<end;start++,end--) {
            int temp =arr6[start];
            arr6[start]=arr6[end];
            arr6[end]=temp;
        }
        
    }
    
    @Test
    public void t11(){
     // s数组反转
      
      String s="mnaanm";
     char[] ch = s.toCharArray();
     boolean flag=true;
     for(int start=0,end=ch.length-1;start<=end;start++,end--){
         if(ch[start]!=ch[end]){
             flag=false;
             break;
         }
              
     }
     if(flag){
         System.err.println("yes");
     }else{
         System.err.println("no");
     }
       
    }
    
    
}
