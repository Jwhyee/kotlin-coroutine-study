package design.patterns.study.jwhy.chapter02.part12

import kotlinx.coroutines.*
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume

suspend fun main(): Unit =
   withContext(newSingleThreadContext("Thread1")) {
      var cont: Continuation<Unit>? = null

      launch(newSingleThreadContext("Thread2")) {
         delay(1000)
         cont?.resume(Unit)
      }

      launch(Dispatchers.Unconfined) {
         println("p1 = ${Thread.currentThread().name}")

         suspendCancellableCoroutine<Unit> {
            cont = it
         }

         println("p2 = ${Thread.currentThread().name}")

         delay(1000)

         println("p3 = ${Thread.currentThread().name}")
      }
   }