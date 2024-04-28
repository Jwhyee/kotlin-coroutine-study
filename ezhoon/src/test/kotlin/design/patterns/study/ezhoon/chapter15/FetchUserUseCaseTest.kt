package design.patterns.study.ezhoon.chapter15

import coroutine.study.sample.ezhoon.chapter15.FetchUserUseCase
import coroutine.study.sample.ezhoon.chapter15.UserDataRepository
import design.patterns.study.ezhoon.BaseTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalCoroutinesApi::class)
class FetchUserUseCaseTest : BaseTest() {

    private val userRepo: UserDataRepository = mockk()

    private lateinit var fetchUserUseCase: FetchUserUseCase

    /**
     * given
     * - getName() [GET_NAME_DELAY]
     * - getFriends() [GET_FRIENDS_DELAY]
     * - getProfile() [GET_PROFILE_DELAY]
     *
     * when
     * - fetchUserData()
     *
     * then
     * - name = "ezhoon"
     * - currentTime = 800([GET_PROFILE_DELAY])
     */
    @Test
    fun `fetch user`() = runTest {
        // given
        fetchUserUseCase = FetchUserUseCase(
            repository = userRepo,
            dispatcher = mainCoroutineExtensions.testDispatcher
        )
        coEvery { userRepo.getName() } coAnswers {
            delay(GET_NAME_DELAY)
            "ezhoon"
        }

        coEvery { userRepo.getFriends() } coAnswers {
            delay(GET_FRIENDS_DELAY)
            emptyList()
        }

        coEvery { userRepo.getProfile() } coAnswers {
            delay(GET_PROFILE_DELAY)
            "profile"
        }

        // when
        val user = fetchUserUseCase.fetchUserData()
        advanceTimeBy(800)

        // then u
        user.name shouldBe "ezhoon"
        currentTime shouldBe GET_PROFILE_DELAY
    }

    companion object {
        private val GET_PROFILE_DELAY = 800.milliseconds
        private val GET_FRIENDS_DELAY = 700.milliseconds
        private val GET_NAME_DELAY = 600.milliseconds
    }
}

infix fun <T : Any> T?.shouldBe(expected: T?) = this.apply { assertEquals(expected, this) }