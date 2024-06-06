package design.patterns.study.jwhy.chapter02.part11

import kotlinx.coroutines.*

fun CoroutineScope.log(text: String) {
   val name = this.coroutineContext[CoroutineName]?.name
   println("[$name] $text")
}

fun main() = runBlocking(CoroutineName("Parent")) {
   log("Before")

   withContext(CoroutineName("Child1")) {
      delay(1000)
      log("Hello 1")
   }

   withContext(CoroutineName("Child2")) {
      delay(1000)
      try {
         throw IllegalArgumentException()
      } catch (e: IllegalArgumentException) {
         println("exception")
      }
      log("Hello 2")
   }

   log("After")
}