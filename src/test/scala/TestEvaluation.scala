package edu.luc.cs.laufer.cs473.expressions

import org.scalatest.FunSuite
import TestFixtures._
import edu.luc.cs.laufer.cs473.expressions.Execute.Store


object MainEvaluationTest extends App
{
  val storeAssignment1: Store = collection.mutable.Map.empty[String, LValue[Int]]
  Execute(storeAssignment1)(assignment1)
  assert(storeAssignment1 == Map("x" -> Cell(5)))

  val storeAssignment2: Store = collection.mutable.Map.empty[String, LValue[Int]]
  Execute(storeAssignment2)(assignment2)
  assert(storeAssignment2 == Map("x" -> Cell(2), "y" -> Cell(5)))

  val storeAssignment3: Store = collection.mutable.Map.empty[String, LValue[Int]]
  Execute(storeAssignment3)(assignment3)
  assert(storeAssignment3 == Map("x" -> Cell(-1)))

  val storeWhile: Store = collection.mutable.Map.empty[String, LValue[Int]]
  Execute(storeWhile)(while1)
  assert(storeWhile == Map("y" -> Cell(0), "r" -> Cell(10), "x" -> Cell(5)))

  val storeCond: Store = collection.mutable.Map.empty[String, LValue[Int]]
  Execute(storeCond)(condTest)
  assert(storeCond ==  Map("r" -> Cell(5), "x" -> Cell(5), "y" -> Cell(3)))




}
