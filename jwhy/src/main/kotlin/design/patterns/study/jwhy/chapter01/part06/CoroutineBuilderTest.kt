package design.patterns.study.jwhy.chapter01.part06

import kotlinx.coroutines.*

fun main() = runBlocking {
   val res1 = async {
      delay(1000)
      "text1"
   }
   val res2 = async {
      delay(1000)
      "text2"
   }
   val res3 = async {
      delay(1000)
      "text3"
   }
   println(res1.await())
   println(res2.await())
   println(res3.await())
}

fun runBlockingTest() {
   runBlocking {
      print("1\t")
//      delay(1000L)
   }
   GlobalScope.launch {
      println("2\t")
   }
   println("3\t")
}