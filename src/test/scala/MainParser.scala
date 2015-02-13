package edu.luc.cs.laufer.cs473.expressions

import org.scalatest.FunSuite

import TestFixtures._

object MainParser extends App {
  val parsedExpr = new ExprParser(complex1string).InputLine.run()
  println(parsedExpr.get)
  println(complex1)
  println(parsedExpr.get == complex1)
  println(behaviors.evaluate(parsedExpr.get))
}

class TestParser extends FunSuite {
  val parsedExpr = new ExprParser(complex1string).InputLine.run()
  test("parser works") { assert(parsedExpr.get === complex1) }
}
