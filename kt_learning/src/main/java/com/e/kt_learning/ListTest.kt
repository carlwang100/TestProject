package com.e.kt_learning

class ListTest {

    fun List<String>.getShortWordsTo(shortWords: MutableList<String>, maxLength: Int) {
        //filter 函数返回所有使传入函数为 true 的元素 {} 后面是传入函数
        this.filterTo(shortWords) { it.length <= maxLength }
        // throwing away the articles
        val articles = setOf("a", "A", "ago", "An", "the", "The")
        //集合相减  就是移除shortWords中包含有articles里面的元素
        shortWords -= articles
    }

    fun testList(){
        val words = "A long time ago in a galaxy far far away".split(" ")
        //mutableListOf 是可读可写
        val shortWords = mutableListOf<String>()
        words.getShortWordsTo(shortWords, 3)
        println(shortWords)
    }
}