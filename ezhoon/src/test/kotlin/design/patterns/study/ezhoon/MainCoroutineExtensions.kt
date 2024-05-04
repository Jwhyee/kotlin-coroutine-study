@file:OptIn(ExperimentalCoroutinesApi::class)

package design.patterns.study.ezhoon

import io.mockk.clearAllMocks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.RegisterExtension

abstract class BaseTest {

    @JvmField
    @RegisterExtension
    val mainCoroutineExtensions = MainCoroutineExtensions()
}

class MainCoroutineExtensions : BeforeEachCallback, AfterEachCallback {

    private val scheduler: TestCoroutineScheduler = TestCoroutineScheduler()

    val testDispatcher: TestDispatcher = StandardTestDispatcher(scheduler)

    override fun beforeEach(p0: ExtensionContext?) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun afterEach(p0: ExtensionContext?) {
        Dispatchers.resetMain()
        clearAllMocks()
    }
}
