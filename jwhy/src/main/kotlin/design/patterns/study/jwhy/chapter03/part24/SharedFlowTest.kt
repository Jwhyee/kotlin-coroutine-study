package design.patterns.study.jwhy.chapter03.part24

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

suspend fun main() {
//   sharedFlowTest()
//   sharedFlowTest2()
   sharedFlowTest3()
}

private suspend fun sharedFlowTest() = coroutineScope {
   val mutableSharedFlow = MutableSharedFlow<String>(replay = 0)

   launch {
      mutableSharedFlow.collect {
         println("#1 receive $it")
      }
   }

   launch {
      mutableSharedFlow.collect {
         println("#2 receive $it")
      }
   }

   delay(1000)
   mutableSharedFlow.emit("Message1")
   mutableSharedFlow.emit("Message2")
}

suspend fun sharedFlowTest2() = coroutineScope {
   val mutableSharedFlow = MutableSharedFlow<String>(2)
   mutableSharedFlow.emit("Message1")
   mutableSharedFlow.emit("Message2")
   mutableSharedFlow.emit("Message3")

   println(mutableSharedFlow.replayCache)

   launch {
      mutableSharedFlow.collect {
         println("#1 received $it")
      }
   }

   delay(100)
   mutableSharedFlow.resetReplayCache()
   println(mutableSharedFlow.replayCache)
}

suspend fun sharedFlowTest3() = coroutineScope {
   val flow = flowOf("A", "B", "C")
      .onEach { delay(1000) }

   val sharedFlow = flow.shareIn(
      scope = this,
      started = SharingStarted.Eagerly
   )

   delay(500)
   launch { sharedFlow.collect { println("#1 $it") } }

   delay(1000)
   launch { sharedFlow.collect { println("#2 $it") } }

   delay(1000)
   launch { sharedFlow.collect { println("#3 $it") } }
}