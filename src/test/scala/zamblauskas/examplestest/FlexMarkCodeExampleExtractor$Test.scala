package zamblauskas.examplestest

import org.scalatest.{FunSuite, Matchers}
import zamblauskas.examplestest.Util._

class FlexMarkCodeExampleExtractor$Test extends FunSuite with Matchers {

  test("extract all code blocks") {
    val examples = FlexMarkCodeExampleExtractor.extract(readResource("README.md"))

    examples shouldBe Seq(
      CodeExample("scala", "code line 1\n"),
      CodeExample("", "unkown code\n"),
      CodeExample("scala", "code line 1\ncode line 2\n"),
      CodeExample("html", "html code\n")
    )
  }
}
