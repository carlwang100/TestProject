// IBookManager.aidl
package com.example.wangchao.testproject;

// Declare any non-default types here with import statements
import com.example.wangchao.testproject.Book;
interface IBookManager {
     String getBooks();
     void addBook(String book);
}
