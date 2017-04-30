package edu.luc.cs.laufer.cs473.expressions

import scala.util.parsing.combinator.JavaTokenParsers
import ast._

object CombinatorParser extends JavaTokenParsers {

  /** expr ::= term + term | term - term | term */
  def expr: Parser[Expr] = (
    term ~ "+" ~ term ^^ { case l ~ _ ~ r => Plus(l, r) }
      | term ~ "-" ~ term ^^ { case l ~ _ ~ r => Minus(l, r) }
      | term
      | factor
    )

  /** term ::= factor * factor | factor / factor | factor */
  def term: Parser[Expr] = (
    factor ~ "*" ~ factor ^^ { case l ~ _ ~ r => Times(l, r) }
      | factor ~ "/" ~ factor ^^ { case l ~ _ ~ r => Div(l, r) }
      | factor
    )

  /** factor ::= wholeNumber | ident | ( factor ) */
  def factor: Parser[Expr] = (
    wholeNumber ^^ { case s => Constant(s.toInt) }
      | ident ^^ { case s => Variable(s) }
      | "(" ~> expr <~ ")" ^^ { case e => e }
      //| ("+" ~ factor  | "-" ~ factor | struct )^^ {a => a}
  )

  def statement: Parser[Expr] = (
    (assignment <~ ";" | expr <~ ";" | block | conditional | loop)^^ {a => a}
    )

  def assignment: Parser[Expr] = (
    ident ~ "=" ~ expr ^^ { case s ~ _ ~ r => Assignment(Variable(s), r) }
      //ident ~ { "." ident }* "=" ~ expr ^^ { case s ~ _ ~ r => Assignment(Variable(s), r) }
  )

  def conditional: Parser[Expr] = (
    "if" ~ "(" ~> expr ~ ")" ~ block ~ "else" ~ block ^^ { case a ~ _ ~ b ~ _ ~ c => Conditional(a, b, Some(c))}
      | "if" ~ "(" ~> expr ~ ")" ~ block ^^ { case a ~ _ ~ b => Conditional(a, b, None)}
  )

  def loop: Parser[Expr] = (
    "while" ~ "(" ~> expr ~ ")" ~ block ^^ { case g ~ _ ~ b => While(g, b) }
    )

  def block: Parser[Expr] = (
    "{" ~> rep(statement) <~ "}" ^^ { case ss => Sequence(ss: _*) }
  )

//  def field: Parser[Expr] = (
//    ident ~ ":" ~ expr ^^ { case s ~ _ ~ r => Struct(collection.immutable.Map[Variable(s), r])}
//  )

//  def struct: Parser[Expr] = (
//    "{" ~ "}" | "{" ~ field { "," field }* ~ "}" => Struct()
//  )
}
