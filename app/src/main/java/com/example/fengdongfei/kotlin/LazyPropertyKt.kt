package com.example.fengdongfei.kotlin

/**
lazy()是一个函数, 接受一个Lambda表达式作为参数, 返回一个Lazy类型的实例,这个实例可以作为一个委托, 实现延迟加载属性(lazy property):
第一次调用 get() 时, 将会执行 lazy() 函数受到的Lambda 表达式,然后会记住这次执行的结果, 以后所有对 get() 的调用都只会简单地返回以前记住的结果

var类型属性不能设置为延迟加载属性，因为在lazy中并没有setValue(…)方法
lazy操作符是线程安全的。如果在不考虑多线程问题或者想提高更多的性能，也可以使用 lazy(LazyThreadSafeMode.NONE){ … } 。

在LazyThreadSafetyMode中声明了几种，[Lazy]实例在多个线程之间同步访问的形式：

SYNCHRONIZED：锁定，用于确保只有一个线程可以初始化[Lazy]实例。
PUBLICATION：初始化函数可以在并发访问未初始化的[Lazy]实例值时调用几次，，但只有第一个返回的值将被用作[Lazy]实例的值。
NONE：没有锁用于同步对[Lazy]实例值的访问; 如果从多个线程访问实例，是线程不安全的。此模式应仅在高性能至关重要，并且[Lazy]实例被保证永远不会从多个线程初始化时使用
 */
class LazySample {
    //lazy为延迟加载属性(委托属性
    val lazy: String by lazy {
        println("computed!")
        "my lazy"
    }
}

fun main(args: Array<String>) {
    val sample = LazySample()
    println("lazy = ${sample.lazy}")
    println("lazy = ${sample.lazy}")
}