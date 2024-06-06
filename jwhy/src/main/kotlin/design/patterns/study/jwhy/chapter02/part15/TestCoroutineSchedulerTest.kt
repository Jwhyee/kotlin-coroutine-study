package design.patterns.study.jwhy.chapter02.part15

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher

fun main() {
   val testDispatcher = StandardTestDispatcher()

   CoroutineScope(testDispatcher).launch {
      delay(1)
      println("Done 1")
   }

   CoroutineScope(testDispatcher).launch {
      delay(2)
      println("Done 2")
   }

   testDispatcher.scheduler.advanceTimeBy(3)
//   testDispatcher.scheduler.runCurrent()
}