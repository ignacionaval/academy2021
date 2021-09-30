package com.example.unittest

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.function.ThrowingRunnable

class ParkingMachineTest {

   private val parkingMachine = ParkingMachine()

   /*
   * < 0 - Exception invalido
   * 0-15 Gratis
   * 16-30  $20
   * cada 30 mins $20 mas
   * mas de 3 horas: Exception Ve a Administración
    */

   @Test
   fun Testthatlessthan0throwsexception() {
      Assert.assertThrows(
         Exception::class.java,
         ThrowingRunnable {
            parkingMachine.calculateCost(-1)
         }
      )

      Assert.assertThrows(
         Exception::class.java,
         ThrowingRunnable {
            parkingMachine.calculateCost(-100)
         }
      )
   }

   @Test(expected = Exception::class)
   fun TestExceptionWay2_1() {
      parkingMachine.calculateCost(-1)
   }

   @Test(expected = Exception::class)
   fun TestExceptionWay2_100() {
      parkingMachine.calculateCost(-100)
   }

   @Test
   fun TestThatFrom0To20WillBeFree() {
      assertEquals("Test 0 give free", 0, parkingMachine.calculateCost(0))
      assertEquals("Test 10 give free", 0, parkingMachine.calculateCost(10))
      assertEquals("Test 19 give free", 0, parkingMachine.calculateCost(19))
      assertEquals("Test 20 give free", 0, parkingMachine.calculateCost(20))
      Assert.assertTrue("More than 20 is not free", parkingMachine.calculateCost(21) > 0)
   }

   @Test
   fun TestThatFrom21To30WillCost20() {
      assertEquals("Test 21 gives 20", 20, parkingMachine.calculateCost(21))
      assertEquals("Test 22 gives 20", 20, parkingMachine.calculateCost(22))
      assertEquals("Test 30 gives 20", 20, parkingMachine.calculateCost(30))
   }

   @Test
   fun TestFrom31To3HoursGive20Every30Minutes() {
      assertEquals(40, parkingMachine.calculateCost(31))
      assertEquals(40, parkingMachine.calculateCost(59))
      assertEquals(40, parkingMachine.calculateCost(60))
      assertEquals(60, parkingMachine.calculateCost(61))
      assertEquals(120, parkingMachine.calculateCost(179))
      assertEquals(120, parkingMachine.calculateCost(180))
   }

   @Test
   fun TestThatMoreThan180MinutesThrowsException() {
      Assert.assertThrows(
         GoToManagementException::class.java,
         ThrowingRunnable {
            parkingMachine.calculateCost(181)
         }
      )

      Assert.assertThrows(
         GoToManagementException::class.java,
         ThrowingRunnable {
            parkingMachine.calculateCost(1000)
         }
      )

      try {
         parkingMachine.calculateCost(300)
         assert(false)
      } catch (e: GoToManagementException) {
         assertEquals("Exception message is correct",
            "Mucho tiempo! ve a Administración", e.message)
      }
   }


   @Test
   fun testList() {

      val expected =  listOf(30,50,20)

      assertEquals(expected, listOf(15,25,10).map { it*2 })

   }

}