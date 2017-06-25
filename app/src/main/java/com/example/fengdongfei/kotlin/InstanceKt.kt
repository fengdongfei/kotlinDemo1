package com.example.fengdongfei.kotlin

import android.app.Application
import kotlin.properties.Delegates

/**
 * 单利的几种方式
 * Created by fengdongfei on 2017/6/24.
 */

/**
 * 单利1
 */
class LazyNotThreadSafe {

    companion object{
        val instance by lazy(LazyThreadSafetyMode.NONE) {
            LazyNotThreadSafe()
        }

        //下面是另一种等价的写法, 获取单例使用 get 方法
        private var instance2: LazyNotThreadSafe? = null

        fun get() : LazyNotThreadSafe {
            if(instance2 == null){
                instance2 = LazyNotThreadSafe()
            }
            return instance2!!
        }
    }
}

/**
 * 单利2
 */
class LazyThreadSafeDoubleCheck private constructor(){
    companion object{
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            LazyThreadSafeDoubleCheck()
        }

        private @Volatile var instance2: LazyThreadSafeDoubleCheck? = null

        fun get(): LazyThreadSafeDoubleCheck {
            if(instance2 == null){
                synchronized(this){
                    if(instance2 == null)
                        instance2 = LazyThreadSafeDoubleCheck()
                }
            }
            return instance2!!
        }
    }
}

/**
 * 单利3--->常用写法,通过同伴对象
 */
class LazyThreadSafeStaticInnerObject private constructor(){
    companion object{
        fun getInstance() = Holder.instance
    }

    private object Holder{
        val instance = LazyThreadSafeStaticInnerObject()
    }
}

/**
 * 单利4
 */
class LazyThreadSafeSynchronized private constructor() {
    companion object {
        private var instance: LazyThreadSafeSynchronized? = null

        @Synchronized
        fun get(): LazyThreadSafeSynchronized{
            if(instance == null) instance = LazyThreadSafeSynchronized()
            return instance!!
        }
    }

}

/**
 * 单利5---通过lazy
 */
class lazyThreadInstance private constructor() {

    private object Holder { val INSTANCE = lazyThreadInstance() }
    companion object {
        val instance: lazyThreadInstance by lazy { Holder.INSTANCE }
    }
}

/**
 * 单利--最简单的方式
 */
object instance{

}

class App : Application() {
    companion object {
        var instance: App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}