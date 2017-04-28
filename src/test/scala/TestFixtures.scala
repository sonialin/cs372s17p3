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
  //TODO fix all eval fixtures

  val assignment1Eval = Map("x" -> Cell(5))

  val assignmentString2 = "x = 2; y = 5;"

  val assignment2 = Sequence(Assignment(Variable("x"), Constant(2)), Assignment(Variable("y"), Constant(5)))

  val assignment2Eval = Map("x" -> Cell(2), "y" -> Cell(5))

  val assignmentString3 = "x = ((1 + 2) - (3 * 4)) / 5;"

  val assignment3 = Assignment(Variable("x"), complex2)

  val assignment3Eval = Cell(-1)




  val whileString = "while (y) { r = r + x, y = y - 1 }"

  val whileString2 = "r = 0; y = 2; x = 5; while (y) { r = r + x, y = y - 1 }"

  val while1 = While(Variable("Y"),
    Sequence(
      Assignment(Variable("r"), Plus(Variable("r"), Variable("x"))),
    Assignment(Variable("y"), Plus(Variable("y"), Minus(Variable("y")
      , Constant(1))))))

  val whileEval = Map("y" -> Cell(0), "r" -> Cell(10), "x" -> Cell(5))

  val condString = "if (4) { r = r + x; y = y + 1; }"

  val condString2 = "r = 0; y = 2; x = 5; while (y) { r = r + x, y = y - 1 }"

  val condTest = Conditional(Constant(4), Assignment(Variable("r"), Plus(Variable("r"), Variable("x"))),
    Option(Assignment(Variable("y"), Plus(Variable("y"), Constant(1)))))

  val condEval = Map("r" -> Cell(5), "x" -> Cell(5), "y" -> Cell(3))

  val badStringcond = "if )( { r = r + x; y = y + 1; }"

  val badStringAssignment = "x = ;"

  val badStringWhile = "while <> {pass}"




}
