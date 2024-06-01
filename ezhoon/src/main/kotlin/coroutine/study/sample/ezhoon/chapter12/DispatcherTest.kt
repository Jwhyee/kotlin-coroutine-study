package coroutine.study.sample.ezhoon.chapter12

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

val defaultDispatcherThreadPollSize: Int
    get() = Runtime.getRuntime().availableProcessors()

suspend fun main(): Unit = coroutineScope {
    println(defaultDispatcherThreadPollSize)
    launch {
        printCoroutinesTime(Dispatchers.Default)
    }
}

private suspend fun printCoroutinesTime(
    dispatcher: CoroutineDispatcher
) {
    val test = measureTimeMillis {
        coroutineScope {
            repeat(defaultDispatcherThreadPollSize + 1) {
                launch(dispatcher) {
                    Thread.sleep(1000)
                }
            }
        }
    }
    println("#1 $dispatcher took: $test")
}

