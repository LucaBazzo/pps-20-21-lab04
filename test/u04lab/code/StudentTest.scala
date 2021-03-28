package u04lab.code

import org.junit.jupiter.api.Assertions._
import org.junit.jupiter.api._


class StudentTest {

  import u04lab.code.Lists._

  val cPPS = Course("PPS","Viroli")
  val cPCD = Course("PCD","Ricci")
  val cSDR = Course("SDR","D'Angelo")
  val s1 = Student("mario",2015)
  val s2 = Student("gino",2016)
  val s3 = Student("rino") //defaults to 2017

  @Test def testStudentCourses(): Unit ={
    studentsEnrolling()
    assertEquals(List.Cons("PCD",List.Cons("PPS",List.Nil())), s1.courses)
    assertEquals(List.Cons("PPS",List.Nil()), s2.courses)
    assertEquals(List.Cons("SDR", List.Cons("PCD",List.Cons("PPS",List.Nil()))), s3.courses)
  }

  private def studentsEnrolling() = {
    s1.enrolling(cPPS,cPCD)
    s2.enrolling(cPPS)
    s3.enrolling(cPPS, cPCD, cSDR)
  }

  @Test def testStudentHasTeacher(): Unit ={
    studentsEnrolling()
    assertTrue(s1.hasTeacher("Ricci"))
    assertFalse(s2.hasTeacher("D'Angelo"))
  }

}
