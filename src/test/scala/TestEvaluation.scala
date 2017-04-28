package edu.luc.cs.laufer.cs473.expressions

import org.scalatest.FunSuite
import TestFixtures._
import edu.luc.cs.laufer.cs473.expressions.Execute.Store


object MainEvaluationTest extends App
{
  val store: Store = collection.mutable.Map.empty[String, LValue[Int]]
  Execute(store)(assignment1)
  print(store)



}
class TestEvaluation extends FunSuite
{


}