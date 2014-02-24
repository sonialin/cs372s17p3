package edu.luc.cs.laufer.cs473.expressions

object Calculator extends App {

  def processExpr(input: String): Unit = {
    println("You entered: " + input)
    val result = ExprParser.parseAll(ExprParser.expr, input)
    if (result.isEmpty) {
      println("This expression could not be parsed")
    }
    else {
      import Behaviors._
      val expr = result.get
      println("The parsed expression is: ")
      println(toFormattedString(expr))
      println("It has size " + size(expr) + " and depth " + depth(expr))
      println("It evaluates to " + evaluate(expr))
    }
  }

  if (args.length > 0) {
    processExpr(args mkString " ")
  }
  else {
    print("Enter infix expression: ")
    scala.io.Source.stdin.getLines foreach { line =>
      processExpr(line)
      print("Enter infix expression: ")
    }
  }
}
