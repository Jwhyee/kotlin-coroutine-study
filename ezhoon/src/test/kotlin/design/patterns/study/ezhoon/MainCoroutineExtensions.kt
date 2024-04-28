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

    lateinit var scheduler: TestCoroutineScheduler
        private set

    lateinit var testDispatcher: TestDispatcher
        private set

    override fun beforeEach(p0: ExtensionContext?) {
        scheduler = TestCoroutineScheduler()
        testDispatcher = StandardTestDispatcher(scheduler)
        Dispatchers.setMain(testDispatcher)
    }

    override fun afterEach(p0: ExtensionContext?) {
        Dispatchers.resetMain()
        clearAllMocks()
    }
}
