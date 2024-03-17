package design.patterns.study.jwhy.chapter01.part07

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

suspend fun main() = withContext(CoroutineName("Outer")){
    printName()
    launch(CoroutineName("Inner")) {
        printName()
    }
//    delay(10)
    printName()
}

suspend fun printName() {
    println(coroutineContext[CoroutineName]?.name)
}