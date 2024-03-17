package design.patterns.study.jwhy.chapter01.part08

import design.patterns.study.jwhy.chapter01.part06.runBlockingTest
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking(CoroutineName("main")) {
    val name = coroutineContext[CoroutineName]?.name
    println(name)
    launch(CoroutineName("child")) {
        delay(1000)
        val childContextName = coroutineContext[CoroutineName]?.name
        println(childContextName)
    }
}