package design.patterns.study.jwhy.chapter01.part12

import kotlinx.coroutines.*
import java.util.concurrent.Executors

var i = 0

suspend fun main() {
   parallelismOneTest()
//   executorsTest()
}

suspend fun parallelismOneTest(): Unit = coroutineScope {
   val dispatcher = Dispatchers.Default
      .limitedParallelism(1)
   repeat(10_000) {
      launch(dispatcher) { i++ }
   }
   delay(1000)
   println(i)
}

suspend fun executorsTest(): Unit = coroutineScope {
   val dispatcher = Executors.newSingleThreadExecutor()
      .asCoroutineDispatcher()
   repeat(10_000) {
      launch(dispatcher) { i++ }
   }
   delay(1000)
   dispatcher.close()
   println(i)
}