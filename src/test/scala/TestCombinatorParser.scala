package edu.luc.cs.laufer.cs473.expressions

import org.scalatest.FunSuite

import TestFixtures._

object MainCombinatorParser extends App {
  val parsedExpr = CombinatorParser.parseAll(CombinatorParser.expr, complex1string)
  println(parsedExpr.get)
  println(complex1)
  println(parsedExpr.get == complex1)
  println(behaviors.evaluate(parsedExpr.get))
}

class TestCombinatorParser extends FunSuite {
  val parsedExpr = CombinatorParser.parseAll(CombinatorParser.expr, complex1string)
  val parsedExpr2 = CombinatorParser.parseAll(CombinatorParser.expr, complex1string2)
  val parsedExpr3 = CombinatorParser.parseAll(CombinatorParser.expr, assignmentString)
  val parsedExpr4 = CombinatorParser.parseAll(CombinatorParser.expr, assignmentString2)
  val parsedExpr5 = CombinatorParser.parseAll(CombinatorParser.expr, assignmentString3)
  val parsedExpr6 = CombinatorParser.parseAll(CombinatorParser.expr, whileString)
  val parsedExpr7 = CombinatorParser.parseAll(CombinatorParser.expr, cTest) // test if


  test("parser works 1") { assert(parsedExpr.get === complex1) }
  test("parser works 2") { assert(parsedExpr2.get === complex1) }
  test("assignment 1") {assert(parsedExpr3.get === assignment1)}
  test("assignment 2") {assert(parsedExpr4.get === assignment2)}
  test("assignment 3") {assert(parsedExpr5.get === assignment3)}
  test("while test") {assert(parsedExpr6.get === whileString)}
  test("if statment") {assert(parsedExpr7.get === condString)}
}
