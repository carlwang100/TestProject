package com.e.kt_learning

import java.lang.Double.sum

class main_kt {


    companion object {
        /** 我是main入口函数 **/
        @JvmStatic
        fun main(args: Array<String>) {
            println("wangchao")
            //new BaseLearninng
            var baseLearninng = BaseLearninng()
            println("sum is " + baseLearninng.testSum(3, 6))
            println(baseLearninng.sum(4, 40))
            var contructorTest: ContructorTest = ContructorTest("wangc")
            testAddress()
            testSington()
            testDelegate()
            testList()
        }

        private fun testList() {
                ListTest().testList()
        }

        private fun testDelegate() {
            var d = DelegateTest()
            println(d.p)

            d.p = "NEW"
            println(d.p)


            println(d.lazyValue)
            println(d.lazyValue)

            println("sum is ->>>" + d.sum1(1, 3))

        }

        private fun testSington() {
            var a: Int = SingtonTest.getCoin()
            println("init a is ->>>" + a)

            SingtonTest.addCoin()

            println("update a is ->>>" + SingtonTest.getCoin())
        }

        //参数是ab,返回值是Int
        fun testSum(a: Int, b: Int) : Int{
            return a + b
        }

        fun testAddress(){
            var adress = Adress()
            adress.test()
        }

    }


}
