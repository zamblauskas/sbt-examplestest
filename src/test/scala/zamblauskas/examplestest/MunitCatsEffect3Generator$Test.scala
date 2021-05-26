package zamblauskas.examplestest

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class MunitCatsEffect3Generator$Test extends AnyFunSuite with Matchers {

  test("generate test code") {
    val examples = Seq(
      CodeExample("type 1", "code line 1\n"),
      CodeExample("type 2", "code line 1\ncode line 2\n")
    )

    val generated = MunitCatsEffect3Generator.generate("GeneratorTest", examples)

    generated shouldBe """import munit.CatsEffectSuite
                         |
                         |class GeneratorTestSuite extends CatsEffectSuite {
                         |  test("code block #0") {
                         |code line 1
                         |  }
                         |  test("code block #1") {
                         |code line 1
                         |code line 2
                         |  }
                         |}""".stripMargin
  }
}
