package design.patterns.study.jwhy.chapter01.part09

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

suspend fun massiveRun(action: suspend () -> Unit) {
    coroutineScope {
        val n = 100
        val k = 1000
        val time = measureTimeMillis {
            val jobs = List(n) {
                launch {
                    repeat(k) { action() }
                }
            }
            jobs.forEach { it.join() }
        }
        println("Completed ${n * k} actions in $time ms")
    }
}

var counter = 0
fun main() = runBlocking {
    massiveRun {
        counter++
    }
    println("Counter = $counter")
}