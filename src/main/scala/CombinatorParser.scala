package edu.luc.cs.laufer.cs473.expressions

import scala.util.parsing.combinator.JavaTokenParsers
import ast._

object CombinatorParser extends JavaTokenParsers {

  def superStatement : Parser[Expr] = block | loop | conditional | assignment | expr

  /** expr ::= term { { "+" | "-" } term }* */
  def expr: Parser[Expr] =
    term ~! opt(("+" | "-") ~ term) ^^ {
      case l ~ None          => l
      case l ~ Some("+" ~ r) => Plus(l, r)
      case l ~ Some("-" ~ r) => Minus(l, r)
    }

  /** term ::= factor { { "*" | "/" | "%" } factor }* */
  def term: Parser[Expr] =
    factor ~! opt(("*" | "/" | "%") ~ factor) ^^ {
      case l ~ None          => l
      case l ~ Some("*" ~ r) => Times(l, r)
      case l ~ Some("/" ~ r) => Div(l, r)
      case l ~ Some("%" ~ r) => Mod(l, r)
    }

  /** factor ::= wholeNumber | "+" factor | "-" factor | "(" expr ")" */
  def factor: Parser[Expr] = (
    wholeNumber ^^ { case s => Constant(s.toInt) }
    | "+" ~> factor ^^ { case e => e }
    | "-" ~> factor ^^ { case e => UMinus(e) }
    | "(" ~ expr ~ ")" ^^ { case _ ~ e ~ _ => e }
    | ident  ^^ {case v => Variable(v)}
  )

  def statement: Parser[Expr] = (
    ident ~ "=" ~ expr <~ ";" ^^ { case s ~ _ ~ r => Assignment(Variable(s), r) }
    | "while" ~ "(" ~> expr ~ ")" ~ statement ^^ { case g ~ _ ~ b => While(g, b) }
    | "{" ~> rep(statement) <~ "}" ^^ {ss => Sequence(ss: _*) }
    | "if" ~ "(" ~> expr ~ ")" ~ statement  ~ "else" ~ statement ^^ { case a ~ _ ~ b ~ _ ~ c => Conditional(a, b, Some(c))}
  )
  def assignment: Parser[Expr] =
    ident ~ "=" ~ expr <~ ";" ^^ { case s ~ _ ~ r => Assignment(Variable(s), r) }


  def block: Parser[Expr] =
    "{" ~> rep(statement) <~ "}" ^^ {ss => Sequence(ss: _*) }

  def conditional: Parser[Expr] = (
    "if" ~ "(" ~> expr ~ ")" ~ block ~ "else" ~ block ^^ { case a ~ _ ~ b ~ _ ~ c => Conditional(a, b, Some(c))}
      | "if" ~ "(" ~> expr ~ ")" ~ block ^^ { case a ~ _ ~ b => Conditional(a, b, None)}
  )

  def loop: Parser[Expr] =
    "while" ~ "(" ~> expr ~ ")" ~ block ^^ { case g ~ _ ~ b => While(g, b) }


    /*def statement: Parser[Expr] =
    (assignment | expr <~ ";" | block | conditional | loop)^^ {a => a}*/

}
