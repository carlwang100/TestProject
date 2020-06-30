package com.e.kt_learning

import kotlin.properties.Delegates

class DelegateTest {
     //会调用getValue函数
     var p: String by Delegate()

     val lazyValue: String by lazy {
          println("computed!")
          "Hello"
     }

     val sum: (Int, Int) -> Int = {x: Int, y: Int -> x + y}

     val sum1 = {x: Int, y: Int -> x + y}


}