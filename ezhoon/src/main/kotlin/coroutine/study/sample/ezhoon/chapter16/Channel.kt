package coroutine.study.sample.ezhoon.chapter16

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce


@OptIn(ExperimentalCoroutinesApi::class)
fun CoroutineScope.produceNumbers() = produce {
    repeat(10) {
        delay(100)
        send(it)
    }
}

fun CoroutineScope.launchConsumeProcessor(
    id: Int,
    channel: ReceiveChannel<Int>
) = launch {
    channel.consumeEach { msg ->
        println("#$id received $msg")
    }
}

fun main(): Unit = runBlocking {
    val channel = produceNumbers()
    println("start")
    launchConsumeProcessor(

        id = 2,
        channel = channel
    )
    println("reach")
    launchConsumeProcessor(
        id = 3,
        channel = channel
    )
    println("end")
}
