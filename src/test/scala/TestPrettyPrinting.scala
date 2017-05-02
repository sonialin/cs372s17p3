package edu.luc.cs.laufer.cs473.expressions

import org.scalatest.FunSuite
import behaviors._
import TestFixtures._
import edu.luc.cs.laufer.cs473.expressions.Execute.Store

class TestPrettyPrinting extends FunSuite
{
  test("assignment 1 pretty") {assert(toPrettyPrinting(assignment1) === assignmentString)}
  test("assignment 2 pretty") {assert(toPrettyPrinting(assignment2) === assignmentString2)}
  test("assignment 3 pretty") {assert(toPrettyPrinting(assignment3) === assignmentString3)}
  test("while pretty") { assert(toPrettyPrinting(while1) === whileString)}
  test("if pretty") {assert(toPrettyPrinting(condTest) === condString)}



}