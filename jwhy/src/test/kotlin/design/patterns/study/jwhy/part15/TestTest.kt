package design.patterns.study.jwhy.part15

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TestTest {
   @Test
   fun test1() = runTest {
      assertEquals(0, currentTime)
      delay(1000)
      assertEquals(1000, currentTime)
   }

   @Test
   fun test2() = runTest {
      assertEquals(0, currentTime)
      coroutineScope {
         launch { delay(1000) }
         launch { delay(1500) }
         launch { delay(2000) }
      }
      assertEquals(2000, currentTime)
   }
}