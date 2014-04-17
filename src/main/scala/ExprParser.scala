package edu.luc.cs.laufer.cs473.expressions

import scala.util.parsing.combinator.syntactical.StandardTokenParsers

object ExprParser extends StandardTokenParsers {

  lexical.delimiters += ("(", ")", "+", "-", "*", "/")

  def expr: Parser[Expr] =
    term ~! opt(("+" | "-") ~ expr) ^^ {
      case l ~ None => l
      case l ~ Some("+" ~ r) => Plus(l, r)
      case l ~ Some("-" ~ r) => Minus(l, r)
    }

  def term: Parser[Expr] =
    factor ~! opt(("*" | "/") ~ term) ^^ {
      case l ~ None => l
      case l ~ Some("*" ~ r) => Times(l, r)
      case l ~ Some("/" ~ r) => Div(l, r)
    }

  def factor: Parser[Expr] = (
    numericLit ^^ { case s => Constant(s.toInt) }
  | "+" ~> factor ^^ { case e => e }
  | "-" ~> factor ^^ { case e => UMinus(e) }
  | "(" ~ expr ~ ")" ^^ { case _ ~ e ~ _ => e }
  )

  def parseAll[T](p: Parser[T], in: String): ParseResult[T] =
    phrase(p)(new lexical.Scanner(in))
}
