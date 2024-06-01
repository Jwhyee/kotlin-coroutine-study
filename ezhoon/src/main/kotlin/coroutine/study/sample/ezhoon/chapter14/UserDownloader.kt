package coroutine.study.sample.ezhoon.chapter14

import kotlinx.coroutines.*

interface NetworkService {
    suspend fun fetchUser(): List<String>
}

class FakeNetworkService : NetworkService {

    override suspend fun fetchUser(): List<String> {
        delay(2)
        return listOf("ezhoon")
    }
}

class UserDownloader(
    private val api: NetworkService
) {

    private val users = mutableListOf<String>()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = Dispatchers.IO
        .limitedParallelism(1)

    fun downloaded(): List<String> {
        return users.toList()
    }

    suspend fun fetchUser() {
        val userList = api.fetchUser()
        withContext(dispatcher) {
            users.addAll(userList)
        }
    }
}

suspend fun main() {
    val downloader = UserDownloader(FakeNetworkService())
    coroutineScope {
        repeat(1_000_000) {
            launch {
                downloader.fetchUser()
            }
        }
    }
    println(downloader.downloaded().size)
}