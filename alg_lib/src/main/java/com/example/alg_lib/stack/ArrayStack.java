package com.example.alg_lib.stack;

/**
 * 用数组实现栈
 * */
public class ArrayStack {

    private String[] items = null;
    int size;
    int count;

    ArrayStack(int n){
        items = new String[n];
        size = n; //栈的大小
        count = 0;// 当前栈的元素个数
    }

    public boolean push(String item) {
        if (count == size) return false;
        items[count] = item;
        count++;
        return true;
    }

    public String pop(){
        if (count == 0)return null;
        String item = items[count-1];
        count--;
        return item;
    }
}
