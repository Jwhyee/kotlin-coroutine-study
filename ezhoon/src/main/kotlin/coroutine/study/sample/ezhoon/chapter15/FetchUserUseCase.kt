package coroutine.study.sample.ezhoon.chapter15

import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.seconds

data class UserData(
    val name: String,
    val friends: List<String>,
    val profile: String
)

interface UserDataRepository {
    suspend fun getName(): String
    suspend fun getFriends(): List<String>
    suspend fun getProfile(): String
}

class UserDataRepositoryImpl : UserDataRepository {
    override suspend fun getName(): String {
        delay(1.seconds)
        return "ezhoon"
    }

    override suspend fun getFriends(): List<String> {
        delay(2.seconds)
        return emptyList()

    }

    override suspend fun getProfile(): String {
        delay(3.seconds)
        return "profile"
    }
}

class FetchUserUseCase(
    private val repository: UserDataRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun fetchUserData() = withContext(dispatcher) {

        val name = async { repository.getName() }
        val friends = async { repository.getFriends() }
        val profile = async { repository.getProfile() }

        UserData(name.await(), friends.await(), profile.await())
    }
}