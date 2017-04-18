package edu.luc.cs.laufer.cs473.expressions

import org.scalatest.FunSuite
import TestFixtures._

import scala.util.Failure

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

  // tests for added functionality
  val parsedExpr3 = CombinatorParser.parseAll(CombinatorParser.expr, assignmentString)
  val parsedExpr4 = CombinatorParser.parseAll(CombinatorParser.expr, assignmentString2)
  val parsedExpr5 = CombinatorParser.parseAll(CombinatorParser.expr, assignmentString3)
  val parsedExpr6 = CombinatorParser.parseAll(CombinatorParser.expr, whileString)
  val parsedExpr7 = CombinatorParser.parseAll(CombinatorParser.expr, condString) // test if

  //tests that should result in failures
  val parsedExpr8 = CombinatorParser.parseAll(CombinatorParser.expr, badStringcond)
  val parsedExpr9 = CombinatorParser.parseAll(CombinatorParser.expr, badStringAssignment)
  val parsedExpr10 = CombinatorParser.parseAll(CombinatorParser.expr, badStringWhile)


  test("parser works 1") { assert(parsedExpr.get === complex1) }
  test("parser works 2") { assert(parsedExpr2.get === complex1) }


  test("assignment 1") {assert(parsedExpr3.get === assignment1)}
  test("assignment 2") {assert(parsedExpr4.get === assignment2)}
  test("assignment 3") {assert(parsedExpr5.get === assignment3)}
  test("while test") {assert(parsedExpr6.get === while1)}
  test("if statement") {assert(parsedExpr7.get === condTest)}

  test("fail if") {assert(parsedExpr8.get === CombinatorParser.Failure("Parse Error", null))}
  test("fail assignment") {assert(parsedExpr9.get === CombinatorParser.Failure("Parse Error", null))}
  test("fail while") {assert(parsedExpr10.get === CombinatorParser.Failure("Parse Error", null))}

}

