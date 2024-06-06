package design.patterns.study.jwhy.chapter02.part11

import kotlinx.coroutines.*

//fun main() = runBlocking(CoroutineName("Parent")) {
//   log("Before")
//
//   supervisorScope {
//      log("IN")
//      launch {
//         delay(1000)
//         throw Error()
//      }
//   }
//
//   log("After")
//}

fun main() = runBlocking(CoroutineName("Parent")) {
   log("Before")

   withContext(SupervisorJob()) {
      log("IN")
      launch {
         delay(1000)
         throw Error()
      }
   }

   log("After")
}