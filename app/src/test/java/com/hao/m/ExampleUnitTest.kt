package com.hao.m

import io.reactivex.Observable
import io.reactivex.functions.Consumer
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    //in 语法使用，!in不是区间内
    @Test
    fun main() {
        val x = 5
        if (x in 1..8) {
            println("$x"+"在区间内")
        }
    }

    //is检测一个值是否是一个特定类型的值
    fun testIs(x: Any) = when(x) {
        is String -> x.startsWith("prefix")
        else -> false
    }
    @Test
    fun testIs(){
        print(testIs("prefixfdsf"))
    }

    //When 表达式  将它的参数和所有的分支条件顺序比较，直到某个分支满足条件。
    fun testWhen(x :Any){
        when (x) {
            1 -> print("x == 1")
            2 -> print("x == 2")
            else -> { // 注意这个块
                print("x 不是 1 ，也不是 2")
            }
        }
    }

    @Test
    fun testWhen(){
       testWhen(1)
    }

    // 测试lambda(匿名函数)
    fun testLambda(args: Array<String>) {
        val sumLambda: (Int, Int) -> Int = {x,y -> x+y}
        println(sumLambda(1,2))  // 输出 3
    }

    //测试vararg可变长参数
    @Test
    fun testVararg() {
        vars(1,2,3,4,5)  // 输出12345
    }

    /*
     fjldskjfdslkf
     */
    fun vars(vararg v:Int){

        for(vt in v){
            print(vt)
        }
    }
    @Test
    fun testJust(){
        Observable.just(1, 2, 3).subscribe { integer -> println(integer) }
    }

}
