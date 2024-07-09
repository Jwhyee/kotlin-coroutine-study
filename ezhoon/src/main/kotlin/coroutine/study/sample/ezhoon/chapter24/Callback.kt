package coroutine.study.sample.ezhoon.chapter24

import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds

suspend fun getData(): Int {
    delay(1000)
    throw Exception("tawet")
    return 10
}

suspend fun main() {
    try {
        coroutineScope {
            val result = runCatching {
                val data = async { getData() }
                data.await()
            }

            result.onSuccess {
                println("onSuccess: $it")
            }.onFailure { e ->
                println("onFailure: ${e.stackTrace.iterator().forEachRemaining { println(it)}}")
            }

            delay(3000)
            println("Process completed")
        }
    } catch (e: Exception) {
        println("======================================")
        println("catch e > ${e.stackTrace.iterator().forEachRemaining { println(it) }}")
    }
}