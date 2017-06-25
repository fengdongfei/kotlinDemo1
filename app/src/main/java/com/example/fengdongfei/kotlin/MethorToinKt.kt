package com.example.fengdongfei.kotlin

/**
 * Kotlin 中 双冒号操作符 表示把一个方法当做一个参数，传递到另一个方法中进行使用，通俗的来讲就是引用一个方法
 */
fun main(args: Array<String>) {
    val numbers = listOf(1, 2, 3)
    val strings = listOf("a", "ab", "abc")

    println(numbers.filter(::isOdd))

    val oddLength = compose(::isOdd, ::length)
    println(strings.filter(oddLength))

    //函数充当参数使用 ：：
    //这里需要注意的是，lock 函数 的第三个参数传入 method 时，要确定参数个数、类型、返回值都和其形参一致
    //如果我们需要调用其他 Class 中的某一个方法是：d::getResult
    //我们在 Class 中的某个方法中使用双冒号调用当前 Class 的内部方法时调动方式为：this::isOdd
    println(lock("param1", "param2", ::getResult))
}

fun isOdd(x: Int) = x % 2 != 0
fun length(s: String) = s.length
fun <A, B, C> compose(f: (B) -> C,
                      g: (A) -> B): (A) -> C {
    return { x -> f(g(x))}
}


/**
 * @param str1 参数1
 * @param str2 参数2
 */
fun getResult(str1: String, str2: String): String = "result is {$str1 , $str2}"

/**
 * @param p1 参数1
 * @param p2 参数2
 * @param method 方法名称
 */
fun lock(p1: String, p2: String, method: (str1: String, str2: String) -> String): String {
    return method(p1, p2)
}