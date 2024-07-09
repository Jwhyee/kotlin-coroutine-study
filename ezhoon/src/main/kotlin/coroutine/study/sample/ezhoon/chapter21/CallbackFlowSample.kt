package coroutine.study.sample.ezhoon.chapter21

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import kotlin.time.Duration.Companion.seconds

suspend fun getData(): Int {
    delay(1000)
    1.seconds
    throw Exception("tawet")
    return 10
}

suspend fun main() = supervisorScope {
    runCatching {
        val test = async { getData() }
        val testResult = test.await()
        println(testResult)
    }.onSuccess {
        println("onSuccess")
    }.onFailure {
        println("onFailure $it")
    }

    delay(10000)
}