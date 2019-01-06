package enumeratum

import upickle.default.{readwriter, ReadWriter}

/**
  * Enum mix-in with default Reader and Writers defined (case sensitive)
  */
trait UPickleEnum[A <: EnumEntry] { self: Enum[A] =>

  implicit val uPickleReadWriter: ReadWriter[A] =
    readwriter[String].bimap[A](_.entryName, self.withName)

}
