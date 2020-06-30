package com.e.kt_learning

import kotlin.reflect.KProperty

class Delegate {

    operator fun getValue(ref: Any?, property: KProperty<*>): String{
        return "$ref, thank you for delegating '${property.name}' to me"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$value has been assigned to '${property.name}' in $thisRef.")
    }
}