package edu.luc.cs.laufer.cs473.expressions

import org.scalatest.FunSuite
import TestFixtures._


object MainEvaluationTest extends App
{
  print(evaluate(assignment1))


}
class TestEvaluation extends FunSuite
{

  test("assignment one eval"){assert(evaluate(assignment1) ===  assignment1Eval)}
  test("assignment two eval"){assert(evaluate(assignment2) ===  assignment2Eval)}
  test("assignment three eval"){assert(evaluate(assignment3) ===  assignment3Eval)}
  test("while eval"){assert(evaluate(while1) ===  whileEval)}
  test("assignment one eval"){assert(evaluate(condTest) ===  condEval)}
}