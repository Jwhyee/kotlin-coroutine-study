package design.patterns.study.jwhy.chapter01.part22

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.coroutineContext

suspend fun main() {
//   onEachTest()
//   onStartTest()
//   onCompletionTest1()
//   onCompletionTest2()
//   onEmptyTest()
//   catchTest()
   flowOnTest()
}

private suspend fun onEachTest() {
   flowOf(1, 2, 3, 4)
      .onEach { println(it) }
      .collect()
}

private suspend fun onStartTest() {
   flowOf(1, 2, 3, 4)
      .onEach { delay(1000) }
      .onStart { println("Before") }
      .collect { println(it) }
}

private suspend fun onCompletionTest1() = coroutineScope {
   flowOf(1, 2)
      .onEach { delay(1000) }
      .onCompletion { println("Completed") }
      .collect { println(it) }
}

private suspend fun onCompletionTest2() = coroutineScope {
   val job = launch {
      flowOf(1, 2)
         .onEach { delay(1000) }
         .onCompletion { println("Completed") }
         .collect { println(it) }
   }
   delay(1100)
   job.cancel()
}

private suspend fun onEmptyTest() = coroutineScope {
   flow<List<Int>> { delay(1000) }
      .onEmpty { emit(emptyList()) }
      .collect { println(it) }
}

private suspend fun catchTest() {
   class MyError(val causeStr: String) : Throwable(causeStr)

   val flow = flow {
      emit(1)
      emit(2)
      throw MyError("ㅅㄱ ㅂㅇ")
   }

   flow.onEach { println("Got $it") }
      .catch { println("Caught $it") }
      .collect { println("Collected $it") }
}

private suspend fun present(place: String, msg: String) {
   val ctx = coroutineContext
   val name = ctx[CoroutineName]?.name
   println("[$name] $msg on $place")
}

private fun messageFlow() = flow {
   present("flow builder", "Message")
   emit("Message")
}

private suspend fun flowOnTest() {
   val users = messageFlow()
   withContext(CoroutineName("Name1")) {
      users
//         .flowOn(CoroutineName("Name3"))
         .onEach { present("onEach", it) }
//         .flowOn(CoroutineName("Name2"))
         .collect { present("collect", it) }
   }
}