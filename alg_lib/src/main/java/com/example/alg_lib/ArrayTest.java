package com.example.alg_lib;

public class ArrayTest {

    //找出占数组绝大多数的那个数字 {2,3,2,2,4,2}
    public void findMoreThanHalfNum(int a[]){
        int count = 1;
        int result =0;
        for (int i = 0; i < a.length; i++){
            if (count == 0){
                result = a[i];
                count = 1;
                continue;
            }
            if (result == a[i]){
                count++;
            }else {
                count--;
            }
        }

        count = 0;
        //判断result出现的次数是否超过了数组的半
        for(int ret : a){
            if (ret == result)count++;
        }
        if (count > a.length/2){
            System.out.println("满足要求的数值是---》》"+ result);
        }else {
            System.out.println("不存在满足要求的数值是---》》");
        }
    }

}
