@file:OptIn(ExperimentalTime::class, ExperimentalTime::class)

package coroutine.study.sample.ezhoon.chapter10

import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

data class News(val publishedAt: String)

class Server {
    suspend fun getNews(): List<News> {
        val result = CoroutineScope(Dispatchers.IO).async {
            delay(1.seconds)
            listOf(News("1"), News("2"))
        }
        return result.await()
    }
}

private val server: Server by lazy { Server() }

suspend fun main() = runBlocking {
    onCreate()
    delay(10.seconds)
}
fun onCreate() {
    getNewsFromApi { news ->
        val sortedNews = news
            .sortedByDescending { it.publishedAt }
        println("showNews $sortedNews")
    }
    println("doSomething")
}

fun getNewsFromApi(block: (List<News>) -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        block.invoke(server.getNews())
    }
}
