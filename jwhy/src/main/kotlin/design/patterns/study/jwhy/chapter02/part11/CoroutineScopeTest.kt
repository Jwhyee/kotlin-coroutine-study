package design.patterns.study.jwhy.chapter02.part11

import kotlinx.coroutines.*

/*fun main() = runBlocking {
   val a = coroutineScope {
      delay(1000)
      10
   }
   println("a is calculated")
   val b = coroutineScope {
      delay(1000)
      20
   }
   println(a)
   println(b)
}*/

suspend fun longTask() = coroutineScope {
   println("launch")
   launch {
      delay(1000)
      val name = coroutineContext[CoroutineName]?.name
      println("[${name}] Finished task1")
   }
   launch {
      delay(2000)
      val name = coroutineContext[CoroutineName]?.name
      println("[${name}] Finished task2")
   }
}

fun main(): Unit = runBlocking {
   println("Before")
   val job = launch(CoroutineName("Parent")) {
      longTask()
   }
   println("delay")
   delay(1500)
   job.cancel()
   println("After")
}
