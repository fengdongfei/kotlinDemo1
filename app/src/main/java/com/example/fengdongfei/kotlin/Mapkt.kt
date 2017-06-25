/**
 * hashMapOf 测试
 */
class MHashMapOf{
    fun main(args: Array<String>) {
        val map = hashMapOf<String, Int>()
        map.put("one", 1)
        map.put("two", 2)

        for ((key, value) in map) {
            println("key = $key, value = $value")
        }
    }
}


/**
 *n 委托--对于可变值，可以使用 MutableMap 代替，并导入扩展方法 kotlin.properties.setValue。
 */
class MutablePerson(val map: MutableMap<String, Any?>) {
    var name: String by map
    var age: Int     by map
}


/**
 * 扁平化集合flatmap的处理api
 */
class mapP {
    constructor()

    //out 代表String类型的继承者，作为return的类型
    fun main(args: Array<out String>) {
        if (args.size == 0) {
            println("Please provide a name as a command-line argument")
            return
        }
        //in 表达式,循环🏪
        for (name in args)
            println("Hello, $name!")

        //给map传一个lambda表达式
        args.map {
            println(it)
        }

        var i = 0
        while (i < args.size)
            println(args[i++] + "---")

        println("Hello, ${args[0]}!")

        for (i in args.indices)
            println(args[i])
    }

    //map与flatmap的使用
    fun flatmap(args: Array<String>) {
        args.flatMap {
            it.split("_")
        }.map { println(it + "${it.length}") }
    }
}

var sts = arrayOf<String>("1", "2", "3")
val ms = arrayOf("hello_ben_word")
fun main(args: Array<String>) {
    mapP().main(sts)
    mapP().flatmap(ms)
}