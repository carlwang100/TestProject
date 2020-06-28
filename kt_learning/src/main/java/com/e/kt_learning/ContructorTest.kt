package com.e.kt_learning

class ContructorTest : Base{

    var name: String = "wangchao"
    var state: String? = null

    init {
        println("int blocks")
    }
    constructor(name: String) {
        println("int constructor--->>>" + name)
    }

    override fun foo() {
        super.foo()
        println("")
    }

}