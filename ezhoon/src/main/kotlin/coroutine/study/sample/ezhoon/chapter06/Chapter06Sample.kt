package coroutine.study.sample.ezhoon.chapter06

import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun main() = runBlocking {
    val res1 = GlobalScope.async {
        delay(1.seconds)
        "Text 1"
    }

    val res2 = GlobalScope.async {
        delay(3.seconds)
        "Text 2"
    }

    val res3 = GlobalScope.async {
        delay(2.seconds)
        "Text 3"
    }

    println(res1.await())
    println(res2.await())
    println(res3.await())
}