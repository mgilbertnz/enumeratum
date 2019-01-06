package enumeratum

import org.scalatest._
import upickle.default
import upickle.default.{read, writeJs}

/**
  * Created by Lloyd on 12/12/15.
  */
class UPickleSpec extends FunSpec with Matchers {

  import Dummy._

  describe("Reader") {

    val reader: default.Reader[Dummy] = UPickler.reader(Dummy)

    it("should work with valid values") {
      read(ujson.Str("A"))(reader) shouldBe A
    }

    it("should fail with invalid values") {
      intercept[Exception] {
        read(ujson.Str("D"))(reader)
      }
      intercept[Exception] {
        read(ujson.Num(2))(reader)
      }
    }

  }

  describe("insensitive reader") {
    val reader = UPickler.reader(Dummy, true)

    it("should work with strings, disgregarding case") {
      read(ujson.Str("A"))(reader) shouldBe A
      read(ujson.Str("a"))(reader) shouldBe A
    }

    it("should work with invalid values") {
      intercept[Exception](read(ujson.Str("D"))(reader))
      intercept[Exception](read(ujson.Num(5))(reader))
    }

  }

  describe("lower case reader") {
    val reader = UPickler.readerLowercaseLower(Dummy)

    it("should work with strings, lower case") {
      read(ujson.Str("a"))(reader) shouldBe A
    }

    it("should work with invalid values") {
      intercept[Exception](read(ujson.Str("D"))(reader))
      intercept[Exception](read(ujson.Num(5))(reader))
    }

  }

  describe("upper case reader") {
    val reader = UPickler.readerUppercaseOnly(Dummy)

    it("should work with strings, upper case") {
      read(ujson.Str("A"))(reader) shouldBe A
    }

    it("should work with invalid values") {
      intercept[Exception](read(ujson.Str("D"))(reader))
      intercept[Exception](read(ujson.Num(5))(reader))
    }

  }

  describe("Writer") {

    val writer = UPickler.writer(Dummy)

    it("should write enum values to JsString") {
      writeJs[Dummy](A)(writer) shouldBe ujson.Str("A")
    }

  }

  describe("Writer lowercase") {

    val writer = UPickler.writerLowercaseOnly(Dummy)

    it("should write enum values to JsString") {
      writeJs[Dummy](A)(writer) shouldBe ujson.Str("a")
    }

  }

  describe("Writer uppercase") {

    val writer = UPickler.writerUppercaseOnly(Dummy)

    it("should write enum values to JsString") {
      writeJs[Dummy](A)(writer) shouldBe ujson.Str("A")
    }

  }

}
