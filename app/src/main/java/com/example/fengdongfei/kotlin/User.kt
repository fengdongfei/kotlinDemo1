package com.example.fengdongfei.kotlin

/**
 * 数据类，使用关键字data，构造函数需要到var修饰符
 */
data internal class User(var id: Int, var name: String) {
    var address: String? = ""

    //伴生对象，相当于java中的staticß
    companion object {
        fun getUser(): User {
            return User(1, "Alex")
        }
    }

}

fun main(args: Array<String>) {
    val user = User.getUser()
    println("name = ${user.name}, id = ${user.id}")
    val (name, id) = User.getUser()
    println("name = $name, id = $id")
    println("name = ${User.getUser().component1()}, id = ${User.getUser().component2()}")
}