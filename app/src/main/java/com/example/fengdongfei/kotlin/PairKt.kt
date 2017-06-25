package com.example.fengdongfei.kotlin

/**
 * Created by fengdongfei on 2017/6/24.
 */

/**
 * 泛形
 */
class Pair<K, V>(val first: K, val second: V) {
    operator fun component1(): K {
        return first
    }

    operator fun component2(): V {
        return second
    }
}

fun main(args: Array<String>) {
    val pair = Pair(1, "one")
    val (num, name) = pair
    println("num = $num, name = $name")
}