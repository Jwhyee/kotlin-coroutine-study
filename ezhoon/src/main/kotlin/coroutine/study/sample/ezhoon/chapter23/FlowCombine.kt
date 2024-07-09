package coroutine.study.sample.ezhoon.chapter23

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlin.time.Duration.Companion.seconds

val stateFlow = MutableStateFlow(10)

suspend fun main(): Unit = coroutineScope {
    stateFlow
        .onEach { delay(1.seconds) }
        .onStart { println("start") }
        .collect { }
}
