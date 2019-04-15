// IBookManager.aidl
package com.example.wangchao.testproject;
import com.example.wangchao.testproject.data.Book;
import com.example.wangchao.testproject.IOnNewBookArriveListen;
// Declare any non-default types here with import statements

interface IBookManager {
   List<Book> getBookList();
   void addBook(in Book book);
   void registerListen(IOnNewBookArriveListen listen);
   void unregisterListen(IOnNewBookArriveListen listen);
}
