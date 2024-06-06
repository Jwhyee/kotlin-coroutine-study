package design.patterns.study.jwhy.chapter02.part12

import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis

suspend fun main() {
//   nextLongTest()
//   sleepTest()
   mixTest()
//   parallelismTest()
}

suspend fun nextLongTest() = coroutineScope {
   repeat(1000) {
      launch(Dispatchers.IO) {
         List(1000) { Random.nextLong() }.maxOrNull()
         val threadName = Thread.currentThread().name
         println("Running in thread : $threadName")
      }
   }
}

suspend fun sleepTest() {
   val time = measureTimeMillis {
      coroutineScope {
         repeat(50) {
            launch(Dispatchers.IO) {
               Thread.sleep(1000)
               val threadName = Thread.currentThread().name
               println("Running in thread : $threadName")
            }
         }
      }
   }
   println(time)
}

suspend fun mixTest() = coroutineScope {
   delay(100)
   yield()
   launch(Dispatchers.Default) {
      println("Running Default : ${Thread.currentThread().name}")
      withContext(Dispatchers.IO) {
         println("Running IO : ${Thread.currentThread().name}")
      }
   }
}

suspend fun parallelismTest(): Unit = coroutineScope {
   launch { printCoroutinesTime(Dispatchers.IO) }
   launch {
      val dispatcher = Dispatchers.IO
         .limitedParallelism(100)
      printCoroutinesTime(dispatcher)
   }
}

suspend fun printCoroutinesTime(
   dispatcher: CoroutineDispatcher
) {
   val test = measureTimeMillis {
      coroutineScope {
         repeat(100) {
            launch(dispatcher) { Thread.sleep(1000) }
         }
      }
   }
   println("$dispatcher took: $test")
}