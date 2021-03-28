package u04lab.code

import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api.Test
import u04lab.code.Lists._
import u04lab.code.Optionals._

class PowerIteratorsTest {

  val factory = new PowerIteratorsFactoryImpl()

  @Test
  def testIncremental() {
    val pi = factory.incremental(5,_+2); // pi produce 5,7,9,11,13,...
    assertEquals(Option.of(5), pi.next());
    assertEquals(Option.of(7), pi.next());
    assertEquals(Option.of(9), pi.next());
    assertEquals(Option.of(11), pi.next());
    assertEquals(List.Cons(5, List.Cons(7, List.Cons(9, List.Cons(11,List.Nil())))), pi.allSoFar()); // elementi già prodotti
    for (i <- 0 until 10) {
      pi.next(); // procedo in avanti per un po'..
    }
    assertEquals(Option.of(33), pi.next()); // sono arrivato a 33
  }

  @Test def testRandom(): Unit ={
    val pi = factory.randomBooleans(4)
    val b1 = pi.next()
    val b2 = pi.next()
    val b3 = pi.next()
    val b4 = pi.next()
    println(b1 + " "+ b2 + " "+ b3 + " "+ b4 + " ")
    println(pi.allSoFar())
    assertEquals(Option.empty, pi.next) //fallisce
    assertEquals(List.Cons(true,List.Cons(false,List.Cons(true,List.Cons(false,List.Nil())))), pi.allSoFar())
  }
}