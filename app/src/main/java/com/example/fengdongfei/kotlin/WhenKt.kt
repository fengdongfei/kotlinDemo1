package com.example.fengdongfei.kotlin

/**
 * when表达式
 */
class NormalP constructor() {
    fun main(args: Array<String>) {
        val language = if (args.size == 0) "EN" else args[0]
        println(when (language) {
            "EN" -> "Hello!"
            "1" -> "Salut!"
            "2" -> "Ciao!"
            is String -> println(language)
            else -> "Sorry, I can't greet you in $language yet"
        })
    }

    fun mains(args: Array<String>) {
        cases("Hello")
        cases(1)
        cases(0L)
        cases(MyClass())
        cases("hello")
    }

    fun cases(obj: Any) {
        when (obj) {
            1 -> println("One")
            "Hello" -> println("Greeting")
            is Long -> println("Long")
            !is String -> println("Not a string")
            else -> println("Unknown")
        }
    }

    fun getMnemonic(color: Color) =
            when (color) {
                Color.RED -> "Richard"
                Color.ORANGE -> "Of"
                Color.YELLOW -> "York"
                Color.GREEN -> "Gave"
                Color.BLUE -> "Battle"
                Color.INDIGO -> "In"
                Color.VIOLET -> "Vain"
            }


    fun getWarmth(color: Color) = when(color) {
        Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
        Color.GREEN -> "neutral"
        Color.BLUE, Color.INDIGO, Color.VIOLET -> "cold"
    }

    class MyClass() {
    }

    fun mix(c1: Color, c2: Color) =
            when (setOf(c1, c2)) {
                setOf(Color.RED, Color.YELLOW) -> Color.ORANGE
                setOf(Color.YELLOW, Color.BLUE) -> Color.GREEN
                setOf(Color.BLUE, Color.VIOLET) -> Color.INDIGO
                else -> throw Exception("Dirty color")
            }

    fun mixOptimized(c1: Color, c2: Color) =
            when {
                (c1 == Color.RED && c2 == Color.YELLOW) ||
                        (c1 == Color.YELLOW && c2 == Color.RED) ->
                    Color.ORANGE

                (c1 == Color.YELLOW && c2 == Color.BLUE) ||
                        (c1 == Color.BLUE && c2 == Color.YELLOW) ->
                    Color.GREEN

                (c1 == Color.BLUE && c2 == Color.VIOLET) ||
                        (c1 == Color.VIOLET && c2 == Color.BLUE) ->
                    Color.INDIGO

                else -> throw Exception("Dirty color")
            }

    fun fizzBuzz(i: Int) = when {
        i % 15 == 0 -> "FizzBuzz "
        i % 3 == 0 -> "Fizz "
        i % 5 == 0 -> "Buzz "
        else -> "$i "
    }

    fun whenFun(obj: Any) {
        when (obj) {
            25 -> println("25")
            "Kotlin" -> println("Kotlin")
            !is String -> println("Not String")
            is Long -> println("Number is Long")
            else -> println("Nothing")
        }
    }

    fun recognize(c: Char) = when (c) {
        in '0'..'9' -> "It's a digit!"
        in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
        else -> "I don't know…​"
    }
}

fun main(args: Array<String>) {
    NormalP().main(sts)

    for (i in 1..100) {
        print(NormalP().fizzBuzz(i))
    }

    //downTo() 倒序输出是downTo 扩展函数可用于一对整数类型值,用于生成倒叙的序列
    for (i in 100 downTo 1 step 2) {
        print(NormalP().fizzBuzz(i))
    }
}