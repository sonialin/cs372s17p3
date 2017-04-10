package edu.luc.cs.laufer.cs473.expressions

import org.scalatest.FunSuite

import TestFixtures._

object MainPegParser extends App {
  val parsedExpr = new PegParser(complex1string).InputLine.run()
  println(parsedExpr.get)
  println(complex1)
  println(parsedExpr.get == complex1)
  println(behaviors.evaluate(parsedExpr.get))
}

class TestPegParser extends FunSuite {
  val parsedExpr = new PegParser(complex1string).InputLine.run()
  val parsedExpr2 = new PegParser(complex1string2).InputLine.run()
  val parsedExpr3 = new PegParser(assignmentString).InputLine.run()
  val parsedExpr4 = new PegParser(assignmentString2).InputLine.run()
  val parsedExpr5 = new PegParser(assignmentString3).InputLine.run()
  val parsedExpr6 = new PegParser(whileString).InputLine.run()
  val parsedExpr7 = new PegParser(cTest).InputLine.run()


  test("parser works 1") { assert(parsedExpr.get === complex1) }
  test("parser works 2") { assert(parsedExpr2.get === complex2) }
  test("assignment 1") { assert(parsedExpr3.get === assignment1) }
  test("assignment 2") { assert(parsedExpr4.get === assignment2) }
  test("assignment 3") { assert(parsedExpr5.get === assignment3) }
  test("while") { assert(parsedExpr6.get === while1) }
  test("if") { assert(parsedExpr7.get === condString) }

}
