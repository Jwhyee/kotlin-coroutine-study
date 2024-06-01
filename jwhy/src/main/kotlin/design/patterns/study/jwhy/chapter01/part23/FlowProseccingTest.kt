package design.patterns.study.jwhy.chapter01.part23

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

suspend fun main() {
//   mergeTest()
//   mergeTestWithDelay()
//   zipTest()
//   combineTest()
   foldTest()
}

private suspend fun mergeTest() {
   val ints = flowOf(1, 2, 3)
   val doubles = flowOf(1.0, 2.0, 3.0)
   val together = merge(ints, doubles)
   println(together.toList())
}

private suspend fun mergeTestWithDelay() {
   val ints = flowOf(1, 2, 3).onEach { delay(100) }
   val doubles = flowOf(1.0, 2.0, 3.0)
   val together = merge(ints, doubles)
   together.collect { println(it) }
}

private suspend fun zipTest() {
   val flow1 = flowOf("A", "B", "C")
      .onEach { delay(400) }

   val flow2 = flowOf(1, 2, 3, 4)
      .onEach { delay(1000) }

   flow1.zip(flow2) { f1, f2 -> "${f1}_${f2}"}
      .collect { println(it) }
}

private suspend fun combineTest() {
   val flow1 = flowOf("A", "B", "C")
      .onEach { delay(400) }

   val flow2 = flowOf(1, 2, 3, 4)
      .onEach { delay(1000) }

   flow1.combine(flow2) { f1, f2 -> "${f1}_${f2}"}
      .collect { println(it) }
}

private suspend fun foldTest() {
   val list = listOf(1, 2, 3, 4)
   val res = list.fold(0) { acc, i -> acc + i }
   println(res)
   val res2 = list.fold(1) { acc, i -> acc * i }
   println(res2)
}

