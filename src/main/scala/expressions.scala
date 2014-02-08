package edu.luc.cs.laufer.cs473.expressions

/**
 * An initial algebra of arithmetic expressions.
 */
sealed trait Expr
case class Constant(value: Int) extends Expr
case class UMinus(expr: Expr) extends Expr
case class Plus(val left: Expr, val right: Expr) extends Expr
case class Minus(val left: Expr, val right: Expr) extends Expr
case class Times(val left: Expr, val right: Expr) extends Expr
case class Div(val left: Expr, val right: Expr) extends Expr
case class Mod(val left: Expr, val right: Expr) extends Expr
