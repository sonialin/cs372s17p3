package edu.luc.cs.laufer.cs473.expressions

import jline.Terminal;
import jline.TerminalFactory;
import jline.console.ConsoleReader;

object CombinatorCalculator extends App {

  def processExpr(input: String): Unit = {
    println("You entered: " + input)
    var result = CombinatorParser.parseAll(CombinatorParser.superStatement, input)

    /*if (result.isEmpty) {
      result = CombinatorParser.parseAll(CombinatorParser.expr, input)
    }*/
    val store = collection.mutable.Map[String, LValue[Int]](
      "x" -> Cell(5),
      "i" -> Cell(0),
      "r" -> Cell(0)
    )

    if (result.isEmpty) {
      println("This expression could not be parsed")
    } else {
      import behaviors._
      val expr = result.get
      println("Memory before input:")
      println(store)
      Execute(store)(expr)
      println("The parsed expression is: ")
      println(toFormattedString(expr))
      println(toPrettyPrinting(expr))
      println("Memory after input:")
      println(store)

    }
  }

  if (args.length > 0) {
    processExpr(args mkString " ")
  } else {
    print("Enter infix expression: ")
    scala.io.Source.stdin.getLines foreach { line =>
      processExpr(line)
      print("Enter infix expression: ")
    }
  }

  //TerminalFactory.get.restore();
}
