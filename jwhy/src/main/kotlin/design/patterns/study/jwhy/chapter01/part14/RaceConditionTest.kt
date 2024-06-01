package design.patterns.study.jwhy.chapter01.part14

import design.patterns.study.jwhy.chapter01.part09.massiveRun
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger

val dispatcher = Dispatchers.IO.limitedParallelism(1)
private var atomicCounter = AtomicInteger()
private var counter = 0

fun main() = runBlocking {
   test2()
}

suspend fun test1() {
   massiveRun {
      atomicCounter.set(atomicCounter.get() + 1)
   }
   println("Counter = ${atomicCounter.get()}")
}

suspend fun test2() {
   massiveRun {
      withContext(dispatcher) {
         println(Thread.currentThread().name)
         counter++
      }
   }
   println(counter)
}