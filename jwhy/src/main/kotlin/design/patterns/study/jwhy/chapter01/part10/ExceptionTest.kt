package design.patterns.study.jwhy.chapter01.part10

import kotlinx.coroutines.*

val handler = CoroutineExceptionHandler { ctx, e ->
    println("Caught $e")
}

fun main(): Unit = runBlocking {
    val scope = CoroutineScope(SupervisorJob() + handler)
    scope.launch {
        delay(1000)
        throw Error("Some error")
    }
    scope.launch {
        delay(2000)
        println("Will be printed")
    }
    delay(3000)
}

fun supervisorScopeExceptionTest(): Unit = runBlocking {
    supervisorScope {
        launch {
            delay(1000)
            throw Error("Some Error")
        }
        launch {
            delay(2000)
            println("Will be printed")
        }
    }
    delay(3000)
    println("Done")
}

fun supervisorJobExceptionTest(): Unit = runBlocking {
    val scope = CoroutineScope(SupervisorJob())
    scope.launch {
        delay(1000)
        throw Error("Some Error")
    }

    scope.launch {
        delay(2000)
        println("Will be printed")
    }

    delay(3000)
}

fun tryCatchExceptionHandlingFailTest() = runBlocking {
    try {
        launch {
            delay(1000)
            throw Error("Some Error")
        }
    } catch (e: Throwable) {
        println("Will not be printed")
    }
    launch {
        delay(2000)
        println("Will not be printed")
    }
    Unit
}