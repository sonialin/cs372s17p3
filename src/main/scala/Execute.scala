package edu.luc.cs.laufer.cs473.expressions

import ast._

/** Something that can be used on the right-hand side of an assignment. */
trait RValue[T] {
  def get: T
}

/** Something that can be used on the left-hand side of an assignment. */
trait LValue[T] extends RValue[T] {
  def set(value: T): LValue[T]
}

/** A cell for storing a value. */
case class Cell[T](var value: T) extends LValue[T] {
  override def get = value
  override def set(value: T) = { this.value = value; this }
}

/** A companion object defining a useful Cell instance. */
object Cell {
  val NULL = Cell(0)
}

/** An interpreter for expressions and statements. */
object Execute {

  type Store = collection.mutable.Map[String, LValue[Int]]
  //type result = Try[Cell]

  def apply(store: Store)(s: Expr): LValue[Int] = s match {
    case Constant(value)    => Cell(value) // result(value)
    case Plus(left, right)  => Cell(apply(store)(left).get + apply(store)(right).get)
    case Minus(left, right) => Cell(apply(store)(left).get - apply(store)(right).get)
    case Times(left, right) => Cell(apply(store)(left).get * apply(store)(right).get)
    case Div(left, right)   => Cell(apply(store)(left).get / apply(store)(right).get)
    case Variable(name)     => store(name) //result(store(name))
    case Assignment(left, right) => {
      val rvalue = apply(store)(right) //result(store)
      val lvalue = apply(store)(left) //result(store)
      lvalue.set(rvalue.get)
      //store.getOrElseUpdate(lvalue.get, rvalue.get)
    }
    case Conditional(condition, block1, block2) => {
      var cvalue = apply(store)(condition)
      if (cvalue.get != 0) {
        apply(store)(block1)
      }
      else {
        apply(store)(block2.get)
      }
    }
    case Sequence(statements @ _*) =>
      statements.foldLeft(Cell.NULL.asInstanceOf[LValue[Int]])((c, s) => apply(store)(s))
    case While(guard, body) => {
      var gvalue = apply(store)(guard)
      while (gvalue.get != 0) {
        apply(store)(body)
        gvalue = apply(store)(guard)
      }
      Cell.NULL
      // type Failure = Cell[Null]
    }
  }
}
