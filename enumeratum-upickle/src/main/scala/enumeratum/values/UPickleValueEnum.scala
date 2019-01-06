package enumeratum.values

import enumeratum.values.UPickler._
import upickle.core.Visitor
import upickle.default.{ReadWriter, Reader}

/**
  * Created by Lloyd on 4/14/16.
  *
  * Copyright 2016
  */
sealed trait UPickleValueEnum[ValueType, EntryType <: ValueEnumEntry[ValueType]] {
  this: ValueEnum[ValueType, EntryType] =>

  /**
    * Implicit UPickle
    */
  implicit def uPickle: ReadWriter[EntryType]

}

/**
  * Enum implementation for Int enum members that contains an implicit UPickle
  */
trait IntUPickleEnum[EntryType <: IntEnumEntry] extends UPickleValueEnum[Int, EntryType] {
  this: ValueEnum[Int, EntryType] =>
  val longImplicit = implicitly[Reader[Int]]

  implicit val uPickle: ReadWriter[EntryType] = {
    new Visitor.MapReader[Any, Int, EntryType](longImplicit) with ReadWriter[EntryType] {
      def write0[Z](out: Visitor[_, Z], v: EntryType) = {
        writer[Int, EntryType](IntUPickleEnum.this).write(out, v)
      }

      override def mapNonNullsFunction(t: Int): EntryType =
        reader[Int, EntryType](IntUPickleEnum.this).visitInt32(t, 0)
    }
  }
}

/**
  * Enum implementation for Long enum members that contains an implicit UPickle
  */
trait LongUPickleEnum[EntryType <: LongEnumEntry] extends UPickleValueEnum[Long, EntryType] {
  this: ValueEnum[Long, EntryType] =>
  val longImplicit = implicitly[Reader[Long]]

  implicit val uPickle: ReadWriter[EntryType] = {
    new Visitor.MapReader[Any, Long, EntryType](longImplicit) with ReadWriter[EntryType] {
      def write0[Z](out: Visitor[_, Z], v: EntryType) = {
        writer[Long, EntryType](LongUPickleEnum.this).write(out, v)
      }

      override def mapNonNullsFunction(t: Long): EntryType =
        reader[Long, EntryType](LongUPickleEnum.this).visitInt64(t, 0)
    }
  }
}

/**
  * Enum implementation for Short enum members that contains an implicit UPickle
  */
trait ShortUPickleEnum[EntryType <: ShortEnumEntry] extends UPickleValueEnum[Short, EntryType] {
  this: ValueEnum[Short, EntryType] =>

  val shortImplicit = implicitly[Reader[Short]]

  implicit val uPickle: ReadWriter[EntryType] = {
    new Visitor.MapReader[Any, Short, EntryType](shortImplicit) with ReadWriter[EntryType] {
      def write0[Z](out: Visitor[_, Z], v: EntryType) = {
        writer[Short, EntryType](ShortUPickleEnum.this).write(out, v)
      }

      override def mapNonNullsFunction(t: Short): EntryType =
        reader[Short, EntryType](ShortUPickleEnum.this).visitInt32(t.toInt, 0)
    }
  }
}

/**
  * Enum implementation for String enum members that contains an implicit UPickle
  */
trait StringUPickleEnum[EntryType <: StringEnumEntry] extends UPickleValueEnum[String, EntryType] {
  this: ValueEnum[String, EntryType] =>

  val stringImplicit = implicitly[Reader[String]]

  implicit val uPickle: ReadWriter[EntryType] = {
    new Visitor.MapReader[Any, String, EntryType](stringImplicit) with ReadWriter[EntryType] {
      def write0[Z](out: Visitor[_, Z], v: EntryType) = {
        writer[String, EntryType](StringUPickleEnum.this).write(out, v)
      }

      override def mapNonNullsFunction(t: String): EntryType =
        reader[String, EntryType](StringUPickleEnum.this).visitString(t, 0)
    }
  }
}

/**
  * Enum implementation for Byte enum members that contains an implicit UPickle
  */
trait ByteUPickleEnum[EntryType <: ByteEnumEntry] extends UPickleValueEnum[Byte, EntryType] {
  this: ValueEnum[Byte, EntryType] =>

  val byteImplicit = implicitly[Reader[Byte]]

  implicit val uPickle: ReadWriter[EntryType] = {
    new Visitor.MapReader[Any, Byte, EntryType](byteImplicit) with ReadWriter[EntryType] {
      def write0[Z](out: Visitor[_, Z], v: EntryType) = {
        writer[Byte, EntryType](ByteUPickleEnum.this).write(out, v)
      }

      override def mapNonNullsFunction(t: Byte): EntryType =
        reader[Byte, EntryType](ByteUPickleEnum.this).visitInt32(t.toInt, 0)
    }
  }
}

/**
  * Enum implementation for Char enum members that contains an implicit UPickle
  */
trait CharUPickleEnum[EntryType <: CharEnumEntry] extends UPickleValueEnum[Char, EntryType] {
  this: ValueEnum[Char, EntryType] =>

  val charImplicit = implicitly[Reader[Char]]

  implicit val uPickle: ReadWriter[EntryType] = {
    new Visitor.MapReader[Any, Char, EntryType](charImplicit) with ReadWriter[EntryType] {
      def write0[Z](out: Visitor[_, Z], v: EntryType) = {
        writer[Char, EntryType](CharUPickleEnum.this).write(out, v)
      }

      override def mapNonNullsFunction(t: Char): EntryType =
        reader[Char, EntryType](CharUPickleEnum.this).visitChar(t, 0)
    }
  }

}
