package enumeratum

import upickle.default.{Reader, Writer}

object UPickler {

  /**
    * Returns a UPickle [[Reader]] for a given [[Enum]]
    *
    * @param enum        the enum you wish to make a Reader for
    * @param insensitive whether or not to match case-insensitively
    */
  def reader[A <: EnumEntry](enum: Enum[A], insensitive: Boolean = false): Reader[A] =
    upickle.default
      .reader[String]
      .map[A](if (insensitive) enum.withNameInsensitive else enum.withName)

  /**
    * Returns a UPickle [[Reader]] for a given [[Enum]]
    *
    * @param enum the enum you wish to make a Reader for transformed to lower case
    */
  def readerLowercaseLower[A <: EnumEntry](enum: Enum[A]): Reader[A] =
    upickle.default.reader[String].map[A](enum.withNameLowercaseOnly)

  /**
    * Returns a UPickle [[Reader]] for a given [[Enum]]
    *
    * @param enum the enum you wish to make a Reader for transformed to upper case
    */
  def readerUppercaseOnly[A <: EnumEntry](enum: Enum[A]): Reader[A] =
    upickle.default.reader[String].map[A](enum.withNameUppercaseOnly)

  /**
    * Returns a [[Writer]] for a given [[Enum]]
    *
    * @param enum [[Enum]] to make a [[Writer]] for
    */
  def writer[A <: EnumEntry](enum: Enum[A]): Writer[A] =
    upickle.default.writer[String].comap[A](_.entryName)

  /**
    * Returns a [[Writer]] for a given [[Enum]], outputted as lower case
    *
    * @param enum [[Enum]] to make a [[Writer]] for
    */
  def writerLowercaseOnly[A <: EnumEntry](enum: Enum[A]): Writer[A] =
    upickle.default.writer[String].comap[A](_.entryName.toLowerCase)

  /**
    * Returns a [[Writer]] for a given [[Enum]], outputted as upper case
    *
    * @param enum [[Enum]] to make a [[Writer]] for
    */
  def writerUppercaseOnly[A <: EnumEntry](enum: Enum[A]): Writer[A] =
    upickle.default.writer[String].comap[A](_.entryName.toUpperCase)

}
