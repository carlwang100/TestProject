package com.e.kt_learning

class Adress {
    var name: String = "wuhan"

    var state: String? = null

    fun copyAddress(adress: Adress): Adress{
        val result = Adress()
        result.name = adress.name
        result.state = "027"
        return result
    }



    val list = mutableListOf(1, 2, 3)

    /**
     * list类中本身没有这个方法，我们自己去扩展，也叫扩展函数
     * */
    fun MutableList<Int>.swap(index1: Int, index2: Int){
        val tmp = this[index1]
        this[index1] = this[index2]
        this[index2] = tmp
    }

    fun test(){

        for (i in list){
            println(i)
        }
        println("变换后")
        list.swap(0, 2)

        for (i in list.indices){
            println(list[i])
        }
    }



}