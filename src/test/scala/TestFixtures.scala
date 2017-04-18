package edu.luc.cs.laufer.cs473.expressions

object TestFixtures {

  import ast._

  val complex1 =
    Div(
      Minus(
        Plus(
          Constant(1),
          Constant(2)
        ),
        Times(
          Constant(3),
          Constant(4)
        )
      ),
      Constant(5)
    )

  val complex1string = "((1 + 2) - (3 * 4)) / 5"

  val complex1string2 = "  ((1 + 2) - (3 * 4)) / 5  "

  val complex2 =
    Mod(
      Minus(
        Plus(
          Constant(1),
          Constant(2)
        ),
        Times(
          UMinus(
            Constant(3)
          ),
          Constant(4)
        )
      ),
      Constant(5)
    )

  val assignmentString = "x = 5;"

  val assignment1 = Assignment(Variable("x"), Constant(5))

  val assignmentString2 = "x = 2; y = 5;"

  val assignment2 = Sequence(Assignment(Variable("x"), Constant(2)), Assignment(Variable("y"), Constant(5)))

  val assignmentString3 = "x = ((1 + 2) - (3 * 4)) / 5;"

  val assignment3 = Assignment(Variable("x"), complex2)

  val whileString = "while (y) { r = r + x, y = y - 1 }"

  val while1 = While(Variable("Y"), Sequence(Assignment(Variable("r"), Plus(Variable("r"), Variable("x"))),
    Assignment(Variable("y"), Plus(Variable("y"), Minus(Variable("y"), Constant(1))))))

  val condString = "if (4) { r = r + x; y = y + 1; }"

  val condTest = Conditional(Constant(4), Assignment(Variable("r"), Plus(Variable("r"), Variable("x"))),
    Option(Assignment(Variable("y"), Plus(Variable("y"), Constant(1)))))

  val badStringcond = "if )( { r = r + x; y = y + 1; }"

  val badStringAssignment = "x = ;"

  val badStringWhile = "while <> {pass}"




}
