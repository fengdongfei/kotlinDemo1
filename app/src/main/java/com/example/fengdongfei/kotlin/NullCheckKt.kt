package com.example.fengdongfei.kotlin

/**
 * Created by fengdongfei on 2017/6/24.
 */
class NullCheckKt constructor() {
    // Return null if str does not hold a number
    fun parseInt(str: String): Int? {
        try {
            return str.toInt()
        } catch (e: NumberFormatException) {
            println("One of the arguments isn't Int")
        }
        return null
    }

    fun nullCheck(args: Array<String>) {
        if (args.size < 2) {
            println("No number supplied");
        } else {
            val x = parseInt(args[0])
            val y = parseInt(args[1])

            // We cannot say 'x * y' now because they may hold nulls
            if (x != null && y != null) {
                println("${x * y}");
            } else {
                println("One of the arguments is null")
            }
        }
    }
}

fun main(args: Array<String>) {
    NullCheckKt().nullCheck(sts)
}