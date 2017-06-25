package com.example.fengdongfei.kotlin

import java.io.BufferedReader
import java.io.StringReader

/**
 * Created by fengdongfei on 2017/6/26.
 */
fun readNumber(reader: BufferedReader): Int? {
    try {
        val line = reader.readLine()
        return Integer.parseInt(line)
    }
    catch (e: NumberFormatException) {
        return null
    }
    finally {
        reader.close()
    }
}
fun readNumber2(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        return
    }

    println(number)
}

fun readNumber3(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        null
    }

    println(number)
}

fun main(args: Array<String>) {
//    val reader = BufferedReader(StringReader("239"))
//    println(readNumber(reader))

//    val reader = BufferedReader(StringReader("not a number"))
//    readNumber(reader)

    val reader = BufferedReader(StringReader("not a number"))
    readNumber(reader)
}