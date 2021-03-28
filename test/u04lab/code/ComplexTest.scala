package u04lab.code

import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api._

class ComplexTest {

  val a = Array(Complex(10,20), Complex(1,1), Complex(7,0))

  @Test def testComplexAdd(): Unit ={
    val c = a(0) + a(1) + a(2)
    assertEquals(Complex(18.0,21.0), c)
  }

  @Test def testComplexMulti(): Unit ={
    val c = a(0) * a(1)
    assertEquals(Complex(-10.0,30.0), c)
  }

  @Test def testComplexEquality(): Unit ={
    val b = Complex(10,20)
    assertTrue(a(0).equals(b))
    assertFalse(a(1).equals(a(2)))
  }

}
