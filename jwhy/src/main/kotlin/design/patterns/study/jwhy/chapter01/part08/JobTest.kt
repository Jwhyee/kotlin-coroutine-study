package design.patterns.study.jwhy.chapter01.part08

import kotlinx.coroutines.*

suspend fun main() = coroutineScope {
    val job = Job()
    launch(job) {
        delay(1000)
        println("Text1")
    }
    launch(job) {
        delay(1000)
        println("Text2")
    }
//    job.join()
    job.children.forEach {
        println(it)
        it.join()
        println(it)
    }
//    println("Will not be printed")
}

suspend fun childJobTest(): Unit = runBlocking {
    val name = CoroutineName("Some name")
    val job = Job()

    launch(name + job) {
        val childName = coroutineContext[CoroutineName]
        println(childName == name)
        val childJob = coroutineContext[Job]
        println(childJob == job)
        println(childJob == job.children.first())
    }
}

suspend fun jobStatus() = coroutineScope {
    val job = Job()
    println("1 : $job")
    job.complete()
    println("2 : $job")

    val activeJob = launch {
        delay(1000)
    }
    println("3 : $activeJob")
    activeJob.join()
    println("4 : $activeJob")

    val lazyJob = launch(start = CoroutineStart.LAZY) {
        delay(1000)
    }
    println("5 : $lazyJob")
    lazyJob.start()
    println("6 : $lazyJob")
    lazyJob.join()
    println("7 : $lazyJob")
}