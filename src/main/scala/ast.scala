package edu.luc.cs.laufer.cs473.expressions.ast

/** An initial algebra of arithmetic expressions. */
sealed trait Expr
case class Constant(value: Int) extends Expr
abstract class UnaryExpr(expr: Expr) extends Expr { require { expr != null } }
case class UMinus(expr: Expr) extends UnaryExpr(expr)
abstract class BinaryExpr(left: Expr, right: Expr) extends Expr { require { (left != null) && (right != null) } }
abstract class TernaryExpr(left: Expr, middle: Expr, right: Expr) extends Expr
case class Plus(left: Expr, right: Expr) extends BinaryExpr(left, right)
case class Minus(left: Expr, right: Expr) extends BinaryExpr(left, right)
case class Times(left: Expr, right: Expr) extends BinaryExpr(left, right)
case class Div(left: Expr, right: Expr) extends BinaryExpr(left, right)
case class Mod(left: Expr, right: Expr) extends BinaryExpr(left, right)

/* source: https://github.com/lucproglangcourse/misc-scala/blob/master/src/main/scala/imperative/syntax.scala */
case class Variable(name: String) extends Expr {
  require(name != null)
}

case class Sequence(statements: Expr*) extends Expr {
  require(statements != null)
  require(!statements.contains(null))
}
case class Conditional(condition: Expr, block1: Expr, block2: Expr) extends TernaryExpr(condition, block1, block2)
case class While(guard: Expr, body: Expr) extends BinaryExpr(guard, body)
case class Assignment(left: Expr, right: Expr) extends Expr
