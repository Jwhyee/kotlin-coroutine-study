package design.patterns.study.jwhy.chapter03.part19

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext

suspend fun main() {
   sequenceTest()
//   flowTest()
}

suspend fun sequenceTest() = withContext(newSingleThreadContext("main")) {
   launch {
      repeat(10) {
         delay(100)
         println("Processing on coroutine")
      }
   }
   val list = getSequence()
   list.forEach { println(it) }
}

fun getSequence(): Sequence<String> = sequence {
   repeat(3) {
      Thread.sleep(1000)
      yield("User$it")
   }
}

suspend fun flowTest() = withContext(newSingleThreadContext("main")) {
   launch {
      repeat(10) {
         delay(100)
         println("Processing on coroutine")
      }
   }
   val list = getFlow()
   list.collect { println(it) }
}

fun getFlow(): Flow<String> = flow {
   repeat(3) {
      delay(1000)
      emit("User$it")
   }
}