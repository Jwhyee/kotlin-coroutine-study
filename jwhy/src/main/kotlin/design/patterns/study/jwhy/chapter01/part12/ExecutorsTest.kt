package design.patterns.study.jwhy.chapter01.part12

import kotlinx.coroutines.asCoroutineDispatcher
import java.util.concurrent.Executors

const val NUMBER_OF_THREADS = 20
fun main() {
   val dispatcher = Executors
      .newFixedThreadPool(NUMBER_OF_THREADS)
      .asCoroutineDispatcher()

   dispatcher.close()
}