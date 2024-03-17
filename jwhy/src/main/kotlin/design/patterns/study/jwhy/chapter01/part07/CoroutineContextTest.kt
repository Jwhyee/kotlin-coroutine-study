package design.patterns.study.jwhy.chapter01.part07

import design.patterns.study.jwhy.chapter01.part06.runBlockingTest
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun main() = runBlocking(CoroutineName("main")) {
   log("start")
   val v1 = async(CoroutineName("c1")) {
      delay(500)
      log("Running async")
      42
   }
   launch(CoroutineName("c2")) {
      delay(3000)
      log("Running launch")
   }
   log("The answer is ${v1.await()}")
}

fun CoroutineScope.log(msg: String) {
   val name = coroutineContext[CoroutineName]?.name
   println("[$name] $msg")
}

//fun contextTest() {
//   val ctx: CoroutineContext = CoroutineName("A name")
//   println(CoroutineName)
//   val coroutineName: CoroutineName? = ctx[CoroutineName]
//   println(coroutineName?.name)
//   val job: Job? = ctx[Job]
//   println(job)
//}