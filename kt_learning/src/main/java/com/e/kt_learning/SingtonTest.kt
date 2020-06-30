package com.e.kt_learning


/**
 *  单例模式
 * */
object SingtonTest {
    private var coin: Int = 0

    fun getCoin():Int{
        return coin
    }

    fun addCoin(){
        coin += 1
    }

}