package design.patterns.study.jwhy.chapter02.part14

import design.patterns.study.jwhy.chapter02.part09.massiveRun
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

val mutex = Mutex()
private var counter = 0

fun main() = runBlocking {
   massiveRun {
      mutex.withLock {
         println(Thread.currentThread().name)
         counter++
      }
   }
   println(counter)
}

/*suspend fun main() = coroutineScope {
   repeat(5) {
      launch { delayAndPrint() }
   }
}*/



suspend fun delayAndPrint() {
   mutex.lock()
   delay(1000)
   println("DONE")
   mutex.unlock()
}