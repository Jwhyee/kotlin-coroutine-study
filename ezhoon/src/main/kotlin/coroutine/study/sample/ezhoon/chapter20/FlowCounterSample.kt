package coroutine.study.sample.ezhoon.chapter20

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.random.Random

fun Flow<*>.counter(): Flow<Int> {
    var counter = 0
    return this.map {
        counter++
        List(100) { Random.nextLong() }.shuffled().sorted()
        counter
    }
}

suspend fun main(): Unit = coroutineScope {
    val f1 = List(1000) { "$it" }.asFlow()
    val f2 = List(1000) { "$it" }.asFlow().counter()


    launch { println(f1.counter().last()) }
    launch { println(f1.counter().last()) }
    launch { println(f2.last()) }
    launch { println(f2.last()) }
}