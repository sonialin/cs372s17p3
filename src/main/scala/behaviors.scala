package edu.luc.cs.laufer.cs473.expressions

import ast._

object behaviors {

  def evaluate(e: Expr): Int = e match {
    case Constant(c) => c
    case UMinus(r)   => -evaluate(r)
    case Plus(l, r)  => evaluate(l) + evaluate(r)
    case Minus(l, r) => evaluate(l) - evaluate(r)
    case Times(l, r) => evaluate(l) * evaluate(r)
    case Div(l, r)   => evaluate(l) / evaluate(r)
    case Mod(l, r)   => evaluate(l) % evaluate(r)
  }

  def size(e: Expr): Int = e match {
    case Constant(c) => 1
    case UMinus(r)   => 1 + size(r)
    case Plus(l, r)  => 1 + size(l) + size(r)
    case Minus(l, r) => 1 + size(l) + size(r)
    case Times(l, r) => 1 + size(l) + size(r)
    case Div(l, r)   => 1 + size(l) + size(r)
    case Mod(l, r)   => 1 + size(l) + size(r)
  }

  def height(e: Expr): Int = e match {
    case Constant(c) => 1
    case UMinus(r)   => 1 + height(r)
    case Plus(l, r)  => 1 + math.max(height(l), height(r))
    case Minus(l, r) => 1 + math.max(height(l), height(r))
    case Times(l, r) => 1 + math.max(height(l), height(r))
    case Div(l, r)   => 1 + math.max(height(l), height(r))
    case Mod(l, r)   => 1 + math.max(height(l), height(r))
  }

  def toFormattedString(prefix: String)(e: Expr): String = e match {
    case Constant(c) => prefix + c.toString
    case UMinus(r)   => buildUnaryExprString(prefix, "UMinus", toFormattedString(prefix + INDENT)(r))
    case Plus(l, r)  => buildExprString(prefix, "Plus", toFormattedString(prefix + INDENT)(l), toFormattedString(prefix + INDENT)(r))
    case Minus(l, r) => buildExprString(prefix, "Minus", toFormattedString(prefix + INDENT)(l), toFormattedString(prefix + INDENT)(r))
    case Times(l, r) => buildExprString(prefix, "Times", toFormattedString(prefix + INDENT)(l), toFormattedString(prefix + INDENT)(r))
    case Div(l, r)   => buildExprString(prefix, "Div", toFormattedString(prefix + INDENT)(l), toFormattedString(prefix + INDENT)(r))
    case Mod(l, r)   => buildExprString(prefix, "Mod", toFormattedString(prefix + INDENT)(l), toFormattedString(prefix + INDENT)(r))
    case Variable(str) => prefix + str.toString
    case Assignment(left, right) => buildExprString(prefix, "VarAssignment", toFormattedString(prefix + INDENT)(left), toFormattedString(prefix + INDENT)(right))
    case While(guard, body) => buildExprString(prefix, "WhileLoop", toFormattedString(prefix + INDENT)(guard), toFormattedString(prefix + INDENT)(body))
    case Conditional(condition, block1, block2) => buildTernaryExprString(prefix, "Conditional", toFormattedString(prefix + INDENT)(condition), toFormattedString(prefix + INDENT)(block1), toFormattedString(prefix + INDENT)(block2.get))
    case Sequence(statements_*) => toFormattedString(statements_*)
    case Field(left, right) => "123"
    case Struct(fields_*) => "456"
  }

  def toFormattedString(e: Expr): String = toFormattedString("")(e)

  def toPrettyPrinting(e: Expr): String = e match {
    case Constant(c) => c.toString
    case UMinus(r)   => "-" + toPrettyPrinting(r)
    case Plus(l, r)  => toPrettyPrinting(l) + " + " + toPrettyPrinting(r)
    case Minus(l, r) => toPrettyPrinting(l) + " - " + toPrettyPrinting(r)
    case Times(l, r) => toPrettyPrinting(l) + " * " + toPrettyPrinting(r)
    case Div(l, r)   => toPrettyPrinting(l) + " / " + toPrettyPrinting(r)
    case Mod(l, r)   => toPrettyPrinting(l) + " % " + toPrettyPrinting(r)
    case Variable(str) => str.toString
    case Assignment(left, right) => toPrettyPrinting(left) + " = " + toPrettyPrinting(right) + ";"
    case While(guard, body) => "while (" + toPrettyPrinting(guard) + ") {" + EOL + "  " + toPrettyPrinting(body) + EOL + "}"
    case Conditional(condition, block1, block2) => "if (" + toPrettyPrinting(condition) + ") {" + EOL + "  " + toPrettyPrinting(block1) + EOL + "} else {" + EOL + "  " + toPrettyPrinting( block2.get) + EOL + "}"
    case Sequence(statements_*) => toPrettyPrinting(statements_*)
    case Field(left, right) => "Field (" + toPrettyPrinting(left) +  ":" + toPrettyPrinting(right)
    case Struct(fields_*) => "Struct (" + fields_*
  }

  def buildExprString(prefix: String, nodeString: String, leftString: String, rightString: String) = {
    val result = new StringBuilder(prefix)
    result.append(nodeString)
    result.append("(")
    result.append(EOL)
    result.append(leftString)
    result.append(", ")
    result.append(EOL)
    result.append(rightString)
    result.append(")")
    result.toString
  }

  def buildUnaryExprString(prefix: String, nodeString: String, exprString: String) = {
    val result = new StringBuilder(prefix)
    result.append(nodeString)
    result.append("(")
    result.append(EOL)
    result.append(exprString)
    result.append(")")
    result.toString
  }

  def buildTernaryExprString(prefix: String, nodeString: String, leftString: String, midString: String, rightString: String) = {
    val result = new StringBuilder(prefix)
    result.append(nodeString)
    result.append("(")
    result.append(EOL)
    result.append(leftString)
    result.append(", ")
    result.append(midString)
    result.append(", ")
    result.append(EOL)
    result.append(rightString)
    result.append(")")
    result.toString
  }

  val EOL = scala.util.Properties.lineSeparator
  val INDENT = ".."
}
