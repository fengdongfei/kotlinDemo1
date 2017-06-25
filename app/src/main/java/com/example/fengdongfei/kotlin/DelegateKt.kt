package com.example.fengdongfei.kotlin

import android.util.Log
import kotlin.properties.Delegates
import kotlin.reflect.KProperty

/**
 * 委托参考资料： http://blog.csdn.net/u014134488/article/details/51123805
 *  1。 代理属性(by)
 *  所谓委托模式 ，就是为其他对象提供一种代理以控制对这个对象的访问
 *
 * 2. kotlin 里面的空是unit类型
 *
 * lazy kotlin委托机制
 *  -->类的委托
 *      b存储为Derived的内部对象，编译器会生成Base的所有方法寄于到b上
 *  -->属性委托
 *      属性的委托指的一个类的某个属性的值不是在类中直接进行定义，而是由某个类的方法来进行 setter 和 getter。默认属性委托都是线程安全的。
 *      属性委托适合那些属性的需要复杂的计算但是计算过程可以被重用的场合。
 *      所谓的委托属性，就是对其属性值的操作不再依赖于其自身的getter()/setter()方法，是将其托付给一个代理类，从而每个使用类中的该属性可以通过代理类统一管理，
 *      再也不用在每个类中，对其声明重复的操作方法
 *  -->标准委托
 *      Kotlin 的标准库中已经内置了很多工厂方法来实现属性的委托。
 *      observable 可以用于实现观察者模式。
 *      定义包含被委托的属性的类--Delegates.observable 接收三个参数：包含委托的属性的元数据的 KProperty 对象，旧值，新值。
 *  -->特别的委托
 *        Kotlin 中有一种特别的委托，可以以 Map 作为一个类的构造方法的参数，访问该类的属性就是访问该 Map 的键值对。这种做法非常类似 Groovy 中的带名构造方法。
 *        要实现这一功能需要得意于 Kotlin 内置的属性的扩展方法 kotlin.properties.getValue
 * lazy 用于进行惰性加载，即第一次使用时才执行初始化的操作。
 *
 *  使用场景
    延迟加载属性(lazy property): 属性值只在初次访问时才会计算,
    可观察属性(observable property): 属性发生变化时, 可以向监听器发送通知,
    将多个属性保存在一个 map 内, 而不是保存在多个独立的域内.
 */

var sts = arrayOf<String>("1", "2", "3")
val ms = arrayOf("hello_ben_word")

class MainActivity {
    fun onCreate() {
        //类委托
        val b = BaseImpl(100)
        Derived(b).print() // prints 10

        //属性委托
        val e = LazyS()
        println(e.msg)  //  msg = Default Message
        e.msg = "New Message"
        println(e.msg)  //  msg = New Message

        //标准委托--observable
        val p = People()
        p.name = "first" //  <init value> -> first
        p.name = "first"
        p.name = "second"    //  first -> second
        println("nickname: $p.nickname") // Log：nam：wang
        p.nickname = "zhang" // Log：kProperty：name | oldName:wang | newName:zhang
        p.nickname = "li" // Log：kProperty：name | oldName:zhang | newName:li

        //标准委托--vetoable
        val p2 = People2()
        p2.age = 10   //  0 -> 10
        println(p2.age)   //  10
        p2.age = 20   //  10 -> 20
        println(p2.age)   //  20
        println("name: $p2.name")
        println("------------------")
        p2.name = "zhangLing"
        println("name: $p2.name")
        println("------------------")
        p2.name = "wangBing"
        println("name: $p2.name")

        //标准委托--notnull
        Foo().notNullBar = "bar"
        Foo().init("Carl")
        println(Foo().notNullBar)

        //特别委托map
        val person = Person(mapOf(
                "name" to "John",
                "age" to 25
        ))
        //委托属性将从这个 map中读取属性值(使用属性名称字符串作为 key 值)。
        println(person.name)
        println(person.age)

        //使用值可变的 MutableMap
        var map:MutableMap<String, Any?> = mutableMapOf(
                "name" to "John Doe",
                "age" to 25)

        val user = MutableUser(map)

        println(user.name) // 打印结果为: "John Doe"
        println(user.age) // 打印结果为: 25

        println("--------------")
        map.put("name", "Green Dao")
        map.put("age", 30)

        println(user.name) // 打印结果为: Green Dao
        println(user.age) // 打印结果为: 30

    }
}

/**
 * 类委托
 */
interface Base {
    fun print()
}
class BaseImpl(val x: Int) : Base {
    override fun print() {
        print(x)
        Log.e("TAG", x.toString())
    }
}
class Derived(b: Base) : Base by b

/**
 *类委托模式--委托的实现依靠于关键字 by
 **/
//Kotlin中，委托的实现依靠于关键字 by ，by表示将抽象主题的实例(by后边的实例)保存在代理类实例的内部,比如SportsManager类继承于ISports接口，
//并可以ISports接口的所有的 public 方法委托给一个指定的对象
interface ISports {
    fun doSports()
}
class SwimForSports: ISports{
    override fun doSports() {
        println("do swim")
    }
}
class SportsManager(sport: ISports): ISports by sport
//在SportsManager声明中，by子句表示，将sport保存在SportsManager的对象实例内部，而且编译器将会生成继承自 ISports 接口的所有方法, 并将调用转发给sport
fun main(args: Array<String>) {
    val swimSports: SwimForSports = SwimForSports()
    SportsManager(swimSports).doSports()// Log：do swim
}

/**
 * 属性委托
 */
//定义一个被委托的类,该类需要包含 getValue() 方法和 setValue() 方法，且参数 thisRef 为进行委托的类的对象，prop 为进行委托的属性的对象
class Delegate {

    private var message = "Default Message"

    operator fun getValue(thisRef: Any?, prop: KProperty<*>): String {
        return "${prop.name} = $message from $thisRef"
    }

    operator fun setValue(thisRef: Any?, prop: KProperty<*>, value: String) {
        message = value
    }

}
//定义包含属性委托的类
/**
 * 定义语法：
    val/var <property name>: <Type> by <expression>
    var/val：属性类型(可变/只读)
    name：属性名称
    Type：属性的数据类型
    expression：代理类
 * by关键字之后的表达式就是委托,属性的get()方法(以及set() 方法)将被委托给这个对象的 getValue()和setValue()方法.属性委托不必实现任何接口,
 * 但必须提供 getValue() 函数(对于 var属性,还需要 setValue() 函数)。
 */
class LazyS {
    var msg: String by Delegate()
}

/**
    标准委托--》observable
    Delegates.observable() 函数接受两个参数: 第一个是初始化值, 第二个是属性值变化事件的响应器(handler).这种形式的委托，采用了观察者模式，
    其会检测可观察属性的变化，当被观察属性的setter()方法被调用的时候，响应器(handler)都会被调用(在属性赋值处理完成之后)并自动执行执行的lambda表达式，
    同时响应器会收到三个参数：被赋值的属性, 赋值前的旧属性值, 以及赋值后的新属性值

    Delegates.observable(wang, hanler),完成了两项工作，一是，将name初始化(name=wang)；二是检测name属性值的变化，每次变化时，
    都会打印其赋值前的旧属性值, 以及赋值后的新属性值。
 */
class People {
    var nickname: String by Delegates.observable("wang", {
        kProperty, oldName, newName ->
        println("kProperty：${kProperty.name} | oldName:$oldName | newName:$newName")
    })

    var name: String by Delegates.observable("Initvalue") {
        prop, old, new ->
        if (old != new) {
            println("\\$old -> $new")
        }
    }
}

/**
    标准委托--》vetoable， 也可以使用 vetoable 代替 observable，该方法拥有布尔类型的返回值，返回 false 的话可以取消对该属性的修改。
    Delegates.vetoable()函数接受两个参数: 第一个是初始化值, 第二个是属性值变化事件的响应器(handler),是可观察属性(Observable)的一个特例，
    不同的是在响应器指定的自动执行执行的lambda表达式中在保存新值之前做一些条件判断，来决定是否将新值保存

    代码示例中的委托，在给name赋值是，只有字符串中含有”wang”时，将新值赋值给name.第一次给name赋值“zhangLing”时，lambda表达式的返回值为false,
    此时并没有对name成功赋值。而第二次，赋值”wangBing” 时，lambda表达式的返回值为true，成功赋值。
 */
class People2 {
    var age: Int by Delegates.vetoable(0) {
        prop, old, new ->
        println("$old -> $new")
        if (new < 20) true else false
    }

    var name: String by Delegates.vetoable("wang", {
        kProperty, oldValue, newValue ->
        println("oldValue：$oldValue | newValue：$newValue")
        newValue.contains("wang")
    })
}

/**
 * 标准委托--》notNull 适用于那些无法在初始化阶段就确定属性值的场合
 * Not Null委托会含有一个可null的变量并会在我们设置这个属性的时候分配一个真实的值。如果这个值在被获取之前没有被分配，它就会抛出一个异常。
 */
class Foo {
    var notNullBar: String by Delegates.notNull<String>()
    fun init(name: String) {
        this.notNullBar = name
    }
}

/**
 * 特别委托map--》访问该类的属性就是访问该 Map 的键值对
 * 类的构造器接受一个map实例作为参数，将map实例本身作为属性的委托，属性的名称与map中的key是一致的，
 * 也就是意味着我们可以很简单的从一个动态地map中创建一个对象实例。
 */
class Person(val map: Map<String, Any?>) {
    val name: String by map
    val age: Int by map
}

/**
 * 特别委托--> MutableMap  对于可变值，可以使用值可变的 MutableMap 代替，并导入扩展方法 kotlin.properties.setValue。
 */
class MutableUser(val map: MutableMap<String, Any?>) {
    val name: String by map
    val age: Int by map
}
