package enumeratum.values

import upickle.default.{Reader, Writer}

/**
  * Created by Lloyd on 4/13/16.
  *
  * Copyright 2016
  */
object UPickler {

  /**
    * Returns a Reader for the given ValueEnum
    */
  def reader[ValueType: Reader, EntryType <: ValueEnumEntry[ValueType]](
      enum: ValueEnum[ValueType, EntryType]
  ): Reader[EntryType] = {
    upickle.default.reader[ValueType].map[EntryType](enum.withValue)
  }

  /**
    * Returns a Writer for the given ValueEnum
    */
  def writer[ValueType: Writer, EntryType <: ValueEnumEntry[ValueType]](
      enum: ValueEnum[ValueType, EntryType]
  ): Writer[EntryType] = {
    upickle.default.writer[ValueType].comap[EntryType](_.value)
  }

}
