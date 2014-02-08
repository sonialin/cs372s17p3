package edu.luc.cs.laufer.cs473.expressions

import org.scalatest.FunSuite

import TestFixtures._

object MainParser {
  def main(args: Array[String]): Unit = {
    val parsedExpr = ExprParser.parseAll(ExprParser.expr, complex1string)
    println(parsedExpr.get)
    println(complex1)
    println(parsedExpr.get == complex1)
    println(Behaviors.evaluate(parsedExpr.get))
  }
}

class TestParser extends FunSuite {
  val parsedExpr = ExprParser.parseAll(ExprParser.expr, complex1string)
  test("parser works") { assert(parsedExpr.get === complex1) }
}
