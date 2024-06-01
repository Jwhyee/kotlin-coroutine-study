package design.patterns.study.jwhy.chapter01.part20

import kotlinx.coroutines.delay

suspend fun main() {
//   flowV1()
//   flowV2()
//   flowV3()
//   flowV4()
//   flowV5()
   flowV6()
}

private suspend fun flowV1() {
   val f: suspend () -> Unit = {
      println("A")
      delay(1000)
      println("B")
      delay(1000)
      println("C")
   }
   f()
   f()
}

private suspend fun flowV2() {
   val f: suspend ((String) -> Unit) -> Unit = { emit ->
      emit("A")
      emit("B")
      emit("C")
   }
   f { println(it) }
   f { println(it) }
}

private fun interface FlowCollector<T> {
   suspend fun emit(value: T)
}

private suspend fun flowV3() {
   val f: suspend (FlowCollector<String>) -> Unit = {
      it.emit("A")
      it.emit("B")
      it.emit("C")
   }
   f { println(it) }
   f { println(it) }
}

private suspend fun flowV4() {
   val f: suspend FlowCollector<String>.() -> Unit = {
      emit("A")
      emit("B")
      emit("C")
   }
   f { println(it) }
   f { println(it) }
}

private interface Flow<T> {
   suspend fun collect(collector: FlowCollector<T>)
}

private suspend fun flowV5() {
   val builder: suspend FlowCollector<String>.() -> Unit = {
      emit("A")
      emit("B")
      emit("C")
   }
   val flow: Flow<String> = object : Flow<String> {
      override suspend fun collect(collector: FlowCollector<String>) {
         collector.builder()
      }
   }
   flow.collect { println(it) }
   flow.collect { println(it) }
}

private fun <T> flow(
   builder: suspend FlowCollector<T>.() -> Unit
) = object : Flow<T> {
   override suspend fun collect(collector: FlowCollector<T>) {
      collector.builder()
   }
}

private suspend fun flowV6() {
   val f: Flow<String> = flow {
      emit("A")
      emit("B")
      emit("C")
   }
   f.collect { println(it) }
   f.collect { println(it) }
}