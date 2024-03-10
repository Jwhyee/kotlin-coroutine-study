package coroutine.study.sample.ezhoon.chapter01

import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

var continuation: Continuation<Unit>? = null

suspend fun suspendSample() {
    suspendCoroutine<Unit> {
        continuation = it
        continuation?.resume(Unit)
    }
}

suspend fun main() {
    println("before")
    suspendSample()
    println("after")
}