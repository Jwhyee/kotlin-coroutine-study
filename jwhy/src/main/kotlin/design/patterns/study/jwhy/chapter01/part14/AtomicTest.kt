package design.patterns.study.jwhy.chapter01.part14

import design.patterns.study.jwhy.chapter01.part09.massiveRun
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger

private var counter = AtomicInteger()

fun main() = runBlocking {
   massiveRun {
      counter.getAndIncrement()
   }
   println("Counter = $counter")
}