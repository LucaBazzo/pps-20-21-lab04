package u04lab.code

import Optionals._
import Lists._
import Streams._

import scala.util.Random

trait PowerIterator[A] {
  def next(): Option[A]
  def allSoFar(): List[A]
  def reversed(): PowerIterator[A]
}

trait PowerIteratorsFactory {

  def incremental(start: Int, successive: Int => Int): PowerIterator[Int]
  def fromList[A](list: List[A])
  def randomBooleans(size: Int): PowerIterator[Boolean]
}

class PowerIteratorsFactoryImpl extends PowerIteratorsFactory {

  private def fromStream[A](stream: Streams.Stream[A]): PowerIterator[A] = {
    case class PowerIteratorImpl() extends PowerIterator[A]{

      private var counter = 0
      private var par_list: List[A] = List.Nil()

      override def next(): Option[A] = {
        counter = counter + 1
        Stream.toList(Stream.take(stream)(counter)) match {
          case List.Cons(h,t) => {
            par_list = List.Cons(h,t)
            List.reverse(par_list) match {
              case List.Cons(h,t) => Option.of(h)
              case _ => Option.empty
            }
          }
          case _ => Option.empty
        }
      }

      override def allSoFar(): List[A] = par_list

      override def reversed(): PowerIterator[A] = {
        par_list = List.reverse(par_list)
        this
      }
    }
    PowerIteratorImpl()
  }

  override def incremental(start: Int, successive: Int => Int): PowerIterator[Int] = {
    this.fromStream(Stream.iterate(start)(successive))
  }

  override def fromList[A](list: List[A]): Unit = {
    //var i = 0
    //this.fromStream(Stream.iterate(list(i)(i = i + 1; list(i)))
  }

  override def randomBooleans(size: Int): PowerIterator[Boolean] = {
    val random = Random
    this.fromStream(Stream.take(Stream.generate(random.nextBoolean))(size))
  }
}
