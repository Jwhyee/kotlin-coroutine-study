@file:OptIn(ExperimentalTime::class)

package coroutine.study.sample.ezhoon.chapter10

import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

fun main(): Unit = runBlocking {

    supervisorScope {

    }
    val supervisorJob = SupervisorJob()

    launch(supervisorJob + Dispatchers.IO) {
        println(this.coroutineContext[supervisorJob.key])
        launch {
            println(this.coroutineContext[supervisorJob.key])
            delay(1.seconds)
            throw Error("test error")
        }
        launch {
            delay(2.seconds)
            println("Will not be printed")
        }
        throw Exception("test")
    }

    delay(3.seconds)
}