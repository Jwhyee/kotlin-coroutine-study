package design.patterns.study.jwhy.chapter02.part08

import kotlinx.coroutines.*

suspend fun main() {
    println("start")
    coroutineScope {
        println("scope")
        launch {
            delay(1000)
            println("launch")
        }
    }
    println("end")
}

fun runBlockingTest(): Unit = runBlocking(CoroutineName("main")) {
    val name = coroutineContext[CoroutineName]?.name
    println(name)
    launch(CoroutineName("child")) {
        delay(1000)
        val childContextName = coroutineContext[CoroutineName]?.name
        println(childContextName)
    }
}