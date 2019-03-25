trait Monad[T] {
  def flatMap[U](f: T => Monad[U]): Monad[U]
}

class MonadObj{

  def readInt: Option[Int] = Some(10)

  def readAndAddOne: Option[Int] = readInt match {
    case Some(x) => Some(x + 1)
    case None => None
  }

  def deadPositiveInt: Option[Int] = readInt match {
    case Some(x) if x >= 0 => Some(x)
    case None => None
  }

  def readAndSum: Option[Int]  = readInt match {
    case Some(x) => readInt match {
      case Some(y) => Some(x + y)
      case None => None
    }

      case None => None

  }
}

object MonadApp extends App{
  val monad = new MonadObj
  monad.readAndSum
}
