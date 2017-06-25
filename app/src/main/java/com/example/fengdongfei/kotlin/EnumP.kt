package com.example.fengdongfei.kotlin

/**
 * 枚举类
 */
enum class enumP(val hello: String) {

    //初始化
    ENGLISH("Hello"),
    CHINESE("你好"),
    JAPANESE("こんにちは"),
    KOREAN("안녕하새요");

    //伴生对象，与java静态类一样，可以类直接调用里面的方法，与类是一一对应的
    companion object {
        fun parse(name: String): enumP {
            return enumP.valueOf(name.toUpperCase())
        }
    }

    fun sayHello() {
        println(hello)
    }

}

//拓展方法-->只要在方法前加上类名就可以
fun enumP.sayBye() {
    val bye = when (this) {
        enumP.ENGLISH -> "Bye"
        enumP.CHINESE -> "再见"
        enumP.JAPANESE -> "さようなら"
        enumP.KOREAN -> "안녕히가세요"
    }
    println(bye)
}

fun main(args: Array<String>) {
    val lang = enumP.parse(enumP.ENGLISH.toString())
    println(lang)
    lang.sayHello()
    lang.sayBye()

    println(Color.BLUE.rgb())
}

enum class Color(
        val r: Int, val g: Int, val b: Int
) {
    RED(255, 0, 0), ORANGE(255, 165, 0),
    YELLOW(255, 255, 0), GREEN(0, 255, 0), BLUE(0, 0, 255),
    INDIGO(75, 0, 130), VIOLET(238, 130, 238);

    fun rgb() = (r * 256 + g) * 256 + b
}

