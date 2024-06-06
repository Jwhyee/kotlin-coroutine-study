package design.patterns.study.jwhy.chapter03.part16

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
//   channelTest()
   channelProduceTest()
}

suspend fun channelTest(): Unit = coroutineScope {
   val channel = Channel<Int>()
   launch {
      repeat(5) { idx ->
         delay(1000)
         println("Producing next one")
         channel.send(idx * 2)
      }
   }
   launch {
      repeat(5) { idx ->
         val receive = channel.receive()
         println(receive)
      }
      channel.consumeEach {
         println(it)
      }
   }
}

suspend fun channelProduceTest(): Unit = coroutineScope{
   val channel = produce {
      repeat(5) {
         println("Producing next one")
         delay(1000)
         send(it * 2)
      }
      channel.close()
   }

   for (i in channel) {
      println(i)
   }
}