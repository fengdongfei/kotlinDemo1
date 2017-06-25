package com.example.fengdongfei.kotlin

/**
 * copy的使用
 */
class copyKt {
    fun main(args: Array<String>) {
        val user = User(1, "Alex")
        println(user) // toString()

        val secondUser = User(1, "Alex")
        val thirdUser = User(2, "Max")

        println("user == secondUser: ${user == secondUser}")
        println("user == thirdUser: ${user == thirdUser}")

        // copy() function
        println(user.copy())
        println(user.copy(name = "Max"))
        println(user.copy(id = 2))
        println(user.copy(2, "Max"))
    }
}

fun main(args: Array<String>) {
    copyKt().main(sts)
}