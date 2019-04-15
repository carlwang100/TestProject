// IOnNewBookArriveListen.aidl
package com.example.wangchao.testproject;
import com.example.wangchao.testproject.data.Book;
// Declare any non-default types here with import statements

interface IOnNewBookArriveListen {
    void OnNewBookArrived(in Book newBook);
}
